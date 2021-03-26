package crawler

import (
	"backend/models"
	"bytes"
	"fmt"
	"github.com/gocolly/colly/v2"
	"github.com/gocolly/colly/v2/queue"
	jsoniter "github.com/json-iterator/go"
	"io/ioutil"
	"log"
	"net/http"
	_ "net/http/pprof"
	"net/url"
	"regexp"
	"strconv"
	"strings"
	"time"
)

// The crawler use database to cache the contents.
// It runs in cron (the timer)

var timeLayouts = []string{"Jan 2006", "Jan. 2006", "January 2006"}

var size = make(chan int, 1)

var c *colly.Collector
var q *queue.Queue
var re *regexp.Regexp

func init() {
	re = regexp.MustCompile(`xplGlobal.document.metadata=(.+"});`)
	c = colly.NewCollector(colly.Async(true))
	c.AllowURLRevisit = true
	// c.SetDebugger(&debug.LogDebugger{})
	err := c.Limit(&colly.LimitRule{DomainGlob: "*", Parallelism: 20})
	if err != nil {
		log.Println("limit fail", err)
	}
	c.OnResponse(onResponse)
}

type searchResult struct {
	TotalRecords int `json:"totalRecords"`
	TotalPages   int `json:"totalPages"`

	Records []struct {
		Link             string `json:"documentLink"`
		Title            string `json:"articleTitle"`
		Code             string `json:"articleNumber"`
		DOI              string `json:"doi"`              // contributor
		PublicationTitle string `json:"publicationTitle"` // contributor
	}
}

type searchDetails struct {
	Code    string `json:"articleNumber"`
	Content string `json:"abstract"`
	Date    string `json:"publicationDate"`
	Words   []struct {
		Kwd []string `json:"kwd"`
	} `json:"keywords"`
}

type searchParam struct {
	Action       string   `json:"action"`
	MatchBoolean bool     `json:"matchBoolean"`
	QueryText    string   `json:"queryText"`
	Highlight    bool     `json:"highlight"`
	ReturnType   string   `json:"returnType"`
	MatchPubs    bool     `json:"matchPubs"`
	PageNumber   string   `json:"pageNumber"` // conf
	RowsPerPage  string   `json:"rowsPerPage"`
	Ranges       []string `json:"ranges"` // 2016_2020_Year
	ReturnFacets []string `json:"returnFacets"`
}

func onResponse(r *colly.Response) {
	contentType := r.Headers.Get("Content-Type")

	if strings.Contains(contentType, "text/html") {
		onHTML(r)
	} else if strings.Contains(contentType, "application/json") {
		onJSON(r)
	} else {
		log.Println("unhandled content type ", contentType)
	}
}

func onJSON(r *colly.Response) {
	body := r.Body
	result := searchResult{}
	err := jsoniter.Unmarshal(body, &result)
	if err != nil {
		log.Println("error parsing json ", err, " ", r.Request.URL.String())
		return
	}
	handleResult(&result)
}

func onHTML(r *colly.Response) {
	s := <-size
	size <- s + 1
	log.Println("onHTML", s, r.Request.URL.String())
	body := r.Body
	group := re.FindSubmatch(body)
	if group == nil {
		log.Println("no match metadata " + r.Request.URL.String())
		return
	}
	details := searchDetails{}
	if err := jsoniter.Unmarshal(group[1], &details); err != nil {
		log.Println("error parsing json ", err, " ", r.Request.URL.String())
		return
	}
	handleDetails(&details)
}

func Start() {

	q, _ = queue.New(10, &queue.InMemoryQueueStorage{
		MaxSize: 10000,
	})
	c.SetRequestTimeout(time.Minute * 2)

	firstPage()

	// TODO remove
	// c.Wait()
	// http.ListenAndServe("0.0.0.0:8899", nil)
}

func firstPage() {
	size <- 1
	param := newSearchParam(1)
	bodyByte, _ := jsoniter.Marshal(&param)
	reader := bytes.NewReader(bodyByte)

	client := &http.Client{}
	req, err := http.NewRequest("POST", "https://ieeexplore.ieee.org/rest/search", reader)
	if err != nil {
		log.Fatalln(err)
	}
	req.Header.Set("Referer", "https://ieeexplore.ieee.org/search/searchresult.jsp")
	req.Header.Set("Content-Type", "application/json")
	req.Header.Set("Accept", "application/json")
	resp, err := client.Do(req)
	if err != nil {
		log.Fatalln(err)
	}
	resBody, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatalln(err)
	}

	result := searchResult{}
	if err := jsoniter.Unmarshal(resBody, &result); err != nil {
		log.Fatalln(err)
	}
	handleResult(&result)

	log.Println(result.TotalPages, result.TotalRecords)

	for i := 2; i <= result.TotalPages; i++ {
		searchParam := newSearchParam(i)
		req := newSearchRequest(searchParam)
		if err := q.AddRequest(req); err != nil {
			log.Println("add request", err)
		}
	}
	err = q.Run(c)
	if err != nil {
		log.Println("run", err)
	}
}

func handleResult(result *searchResult) {
	records := result.Records
	for i := range records {
		paper := models.GetPaperByCode(records[i].Code)
		if paper.ID <= 0 {
			paper.Code = records[i].Code
			doi := strings.ToLower(records[i].DOI)
			pub := strings.ToLower(records[i].PublicationTitle)
			if strings.Contains(doi, "iccv") || strings.Contains(pub, "iccv") {
				paper.Contributor = "iccv"
			} else if strings.Contains(doi, "cvpr") || strings.Contains(pub, "cvpr") {
				paper.Contributor = "cvpr"
			} else if strings.Contains(doi, "eccv") || strings.Contains(pub, "eccv") {
				paper.Contributor = "eccv"
			}
			paper.Link = records[i].Link
			paper.Title = records[i].Title

			models.AddPaper(&paper)

			// get detailsq
			err := c.Visit("https://ieeexplore.ieee.org/document/" + paper.Code)
			// err := q.AddURL("https://ieeexplore.ieee.org/document/" + paper.Code)
			if err != nil {
				log.Println("add url", err)
			}
		} else {
			// log.Println("same code!", paper.Code)
		}
	}
	// q.Run(c.Clone())
}

func handleDetails(details *searchDetails) {
	paper := models.GetPaperByCode(details.Code)
	paper.Content = details.Content
	models.UpdatePaperContent(&paper)

	var t time.Time
	var err error
	if strings.Contains(details.Date, "Sept") {
		index := strings.Index(details.Date, "t")
		details.Date = details.Date[0:index] + details.Date[index+1:]
	}
	for _, layout := range timeLayouts {
		if t, err = time.Parse(layout, details.Date); err == nil {
			break
		}
	}
	if err != nil {
		log.Println("parse time fail", details.Date)
		return
	}

	words := details.Words
	for i := range words {
		kwd := words[i].Kwd
		for j := range kwd {
			models.AddWord(paper.Contributor, kwd[j], t)
		}
	}
}

func newSearchParam(page int) (res *searchParam) {
	nowYear := time.Now().Year()
	res = &searchParam{
		Action:       "search",
		MatchBoolean: true,
		QueryText:    `(("DOI":"cvpr" OR "DOI":"iccv" OR "DOI":"eccv" OR "Publication Title":"cvpr" OR "Publication Title":"eccv" OR "Publication Title":"eccv"))`,
		Highlight:    true,
		ReturnType:   "SEARCH",
		MatchPubs:    true,
		Ranges:       []string{fmt.Sprintf("%d_%d_Year", nowYear-12, nowYear)},
		ReturnFacets: []string{"ALL"},
		RowsPerPage:  "50",
		PageNumber:   strconv.Itoa(page),
	}
	return
}

func newSearchRequest(param *searchParam) *colly.Request {
	bodyByte, _ := jsoniter.Marshal(param)
	reader := bytes.NewReader(bodyByte)
	u, _ := url.Parse("https://ieeexplore.ieee.org/rest/search")
	return &colly.Request{
		URL:    u,
		Method: "POST",
		Headers: &http.Header{
			"Referer":      []string{"https://ieeexplore.ieee.org/search/searchresult.jsp"},
			"Content-Type": []string{"application/json"},
			"Accept":       []string{"application/json"},
		},
		Body: reader,
	}
}

func Search(q []string, page int) (ids []uint, total int) {
	return []uint{}, 0
}
