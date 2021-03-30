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
	Count int    `json:"count" gorm:"column:count(*)"`
}

type TopKey struct {
	Forum string `json:"forum"`
	Name  string `json:"name"`
	Count int    `json:"count" gorm:"column:count(*)"`
}

type Chart struct {
	Forum     string     `json:"forum"`
	Years     []string   `json:"years"`
	KeyValues []KeyValue `json:"key_values"`
}

type KeyValue struct {
	Name   string `json:"name"`
	Counts []int  `json:"counts"`
}

type KeyYearCount struct {
	Name  string `gorm:"column:name"`
	Year  string `gorm:"column:year"`
	Count int    `gorm:"column:count(*)"`
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

//获取某顶会TOP关键词
func GetTOPByForum(forum string, size int) (topkeys []TopKey) {
	db.Raw("SELECT forum, name, count(*) FROM papercrawler.crawler_keyword where forum = '" +
		forum + "' group by name order by count(*) desc").Limit(size).Find(&topkeys)
	return
}

//获取图表所需数据
func GetChart(forum string, topkeys []TopKey) (chart Chart) {

	var keyyearcounts []KeyYearCount
	for _, topkey := range topkeys {
		db.Raw("SELECT name, year, count(*) FROM crawler_keyword WHERE forum = '" + topkey.Forum + "' " +
			"and name = '" + topkey.Name + "'").Group("year").Find(&keyyearcounts)

		var keyvalue KeyValue
		keyvalue.Name = keyyearcounts[1].Name
		if forum == "ECCV" {
			for i := 0; i < 11; i++ {
				keyvalue.Counts = append(keyvalue.Counts, 0)
			}
			for _, keyyearcount := range keyyearcounts {
				index, _ := strconv.Atoi(keyyearcount.Year)          //获取对应的年份
				keyvalue.Counts[(index-2000)/2] = keyyearcount.Count //给对应年份赋值
			}
		} else {
			for i := 0; i < 21; i++ {
				keyvalue.Counts = append(keyvalue.Counts, 0)
			}
			for _, keyyearcount := range keyyearcounts {
				index, _ := strconv.Atoi(keyyearcount.Year)      //获取对应的年份
				keyvalue.Counts[index-2000] = keyyearcount.Count //给对应年份赋值
			}
		}
		chart.KeyValues = append(chart.KeyValues, keyvalue)
	}

	chart.Forum = forum
	if forum == "ECCV" {
		for i := 2000; i <= 2020; i = i + 2 {
			chart.Years = append(chart.Years, strconv.Itoa(i))
		}
	} else {
		for i := 2000; i < 2021; i++ {
			chart.Years = append(chart.Years, strconv.Itoa(i))
		}
	}
	fmt.Println(chart)
	return
}
