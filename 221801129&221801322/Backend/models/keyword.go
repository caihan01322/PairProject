package models

import (
	"fmt"
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

type TopKeyName struct {
	Name  string `json:"name"`
	Count int    `json:"count" gorm:"column:count(*)"`
}

//返回关键词TOP10
func GetTopKeywordList() (results []KeyNum) {
	var tmp1, tmp2 interface{}
	rows, _ := db.Raw("SELECT name, count(*) FROM papercrawler.crawler_keyword group by name order by count(*) desc").Limit(10).Rows()
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

//获取某顶会前几关键词的趋势
func GetTOPByForum(forum string, size int) (topkey []TopKeyName) {
	db.Raw("SELECT forum, name, count(*) FROM papercrawler.crawler_keyword where forum = '" + forum + "' group by name order by count(*) desc").Limit(size).Find(&topkey)
	for i, key := range topkey {
		fmt.Println(i)
		fmt.Printf("%s %d\n", key.Name, key.Count)
	}
	return
}
