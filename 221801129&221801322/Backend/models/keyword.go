package models

import (
	"strconv"
)

type Keyword struct {
	Model
	Name      string `json:"name"`
	Year      string `json:"year"`
	Forum     string `json:"forum"`
	ArticleID uint   `json:"article_id"`
}

type KeyNum struct {
	Name  string `json:"name"`
	Count int    `json:"count"`
}

//返回关键词TOP10
func GetTopKeywordList() (results []KeyNum) {
	var tmp1, tmp2 interface{}
	rows, _ := db.Raw("SELECT count(*), name FROM papercrawler.crawler_keyword group by name order by count(*) desc").Limit(10).Rows()
	defer rows.Close()
	for rows.Next() {
		rows.Scan(&tmp1, &tmp2)
		count, _ := strconv.Atoi(string(tmp1.([]byte)))
		name := string(tmp2.([]byte))
		results = append(results, KeyNum{
			Name:  name,
			Count: count,
		})
	}
	return
}
