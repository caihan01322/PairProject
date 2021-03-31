package models

import (
	"backend/models/json"
	"gorm.io/gorm"
	"gorm.io/gorm/clause"
	"time"
)

type Word struct {
	Model
	Name        string      `json:"name" gorm:"size:255;uniqueIndex:ui"`
	Value       int         `json:"value"`
	Contributor string      `json:"-" gorm:"size:255;uniqueIndex:ui"`
	Points      []WordPoint `json:"points" gorm:"constraint:OnDelete:CASCADE;"`
}

type WordCloud struct {
	Name  string `json:"name"`
	Value int    `json:"value"`
}

func GetWordCloud() (cloud []WordCloud) {
	db.Model(&Word{}).
		Select("name", "SUM(value) as value").
		Group("name").
		Order("value desc").
		Limit(30).
		Find(&cloud)
	return
}

type ChartWord struct {
	Month json.JTime `json:"month"`
	Word  string     `json:"word"`
	Value int        `json:"value"`
}

func GetWords(contributor string) (words []ChartWord) {
	var ids []int
	db.Model(&Word{}).
		Select("id").
		Where("contributor = ?", contributor).
		Limit(10).
		Order("value desc").
		Find(&ids)

	for i := range ids {
		var r []ChartWord
		db.Raw("SELECT wp.time AS month, w.name AS word, wp.value AS value FROM word_point wp "+
			"INNER JOIN word w ON wp.word_id = w.id "+
			"WHERE w.id = ?", ids[i]).Scan(&r)
		words = append(words, r...)
	}
	return
}

var addWordLock = make(chan int, 1)

func AddWord(contributor, name string, t time.Time) {
	word := Word{
		Name:        name,
		Contributor: contributor,
		Value:       1,
	}
	addWordLock <- 1
	// p lock for update, no record selected then no lock
	db.Clauses(clause.Locking{Strength: "UPDATE"}).
		Where("contributor = ? AND name = ?", contributor, name).
		Find(&word)

	if word.ID <= 0 {
		// create, value++ on conflict(update has lock itself)
		db.Clauses(clause.OnConflict{
			Columns:   []clause.Column{{Name: "name"}, {Name: "contributor"}},
			DoUpdates: clause.Assignments(map[string]interface{}{"value": gorm.Expr("value + 1")}),
		}).Create(&word)
	} else {
		// update
		db.Model(&word).Update("value", word.Value+1)
	}
	<-addWordLock
	addWordPoint(t, word)
}
