package models

import (
	"fmt"
	"github.com/thedevsaddam/gojsonq/v2"
	"io/ioutil"
	"os"
)

var dir = "C:\\Users\\xpy91\\Desktop\\json\\data\\CVPR"

func MergeFilename(info os.FileInfo) string {
	return dir + "\\" + info.Name()
}

func InsertArticle() {
	rd, err := ioutil.ReadDir(dir)
	if err != nil {
		fmt.Println("目录错误")
		return
	}
	total, articlenum, key_id := 0, 0, 0
	for _, info := range rd {
		total++
		title := gojsonq.New().File(MergeFilename(info)).Find("title")
		abstract := gojsonq.New().File(MergeFilename(info)).Find("abstract")
		article_id := gojsonq.New().File(MergeFilename(info)).Find("articleNumber")
		link := gojsonq.New().File(MergeFilename(info)).Find("doiLink")
		keywords := gojsonq.New().File(MergeFilename(info)).Find("keywords")
		year := gojsonq.New().File(MergeFilename(info)).Find("publicationYear").(string)

		article := make(map[string]interface{})
		article["title"] = title
		article["abstract"] = abstract
		article["article_id"] = article_id
		article["link"] = link
		AddArticle(article)
		articlenum++

		if keywords != nil {
			list := keywords.([]interface{})

			for i := 0; i < len(list); i++ {
				item := list[i].(map[string]interface{})
				key := item["kwd"].([]interface{})

				for _, one := range key {
					AddKeyword(one.(string), year)
					key_id++
				}
			}
		}
	}
}
