package models

import (
	"fmt"
	"github.com/thedevsaddam/gojsonq/v2"
	"io/ioutil"
	"os"
)

var dir = "C:\\Users\\xpy91\\Desktop\\json\\data\\ECCV"

func MergeFilename(info os.FileInfo) string {
	return dir + "\\" + info.Name()
}

func InsertArticle() {
	rd, err := ioutil.ReadDir(dir)
	if err != nil {
		fmt.Println("目录错误")
		return
	}

	total := 0
	for _, info := range rd {
		total++
		title := gojsonq.New().File(MergeFilename(info)).Find("论文名称")
		abstract := gojsonq.New().File(MergeFilename(info)).Find("摘要")
		articleId := gojsonq.New().File(MergeFilename(info)).Find("原文链接")
		link := gojsonq.New().File(MergeFilename(info)).Find("原文链接")
		keywords := gojsonq.New().File(MergeFilename(info)).Find("关键词")
		year := gojsonq.New().File(MergeFilename(info)).Find("会议和年份")

		article := make(map[string]interface{})
		article["title"] = title
		article["abstract"] = abstract
		article["article_id"] = articleId.(string)[24:]
		article["link"] = link

		var kwds []Keyword
		if keywords != nil {
			key_list := keywords.([]interface{})
			for _, key := range key_list {
				kwds = append(kwds, Keyword{
					Name:  key.(string),
					Year:  year.(string)[len(year.(string))-4:],
					Forum: "ECCV",
				})
			}
		}
		article["keywords"] = kwds
		AddArticle(article)
	}
	fmt.Println(total)
}
