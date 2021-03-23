package models

import (
	"gorm.io/gorm"
)

type Word struct {
	Model
	Name        string      `json:"name"`
	Value       int         `json:"value"`
	Contributor string      `json:"-"`
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

func GetWords(contributor string) (words []Word) {
	var where *gorm.DB
	if contributor == "" {
		where = db
	} else {
		where = db.Where("contributor = ?", contributor)
	}

	where.Limit(10).
		Order("value desc").
		Find(&words)

	for i := range words {
		words[i].Points = []WordPoint{}
		db.Model(words[i]).
			Association("Points").
			Find(&(words[i].Points))
	}
	return
}

func AddWord(contributor, name, time string) {
	word := Word{}
	// 1
	db.Where("contributor = ? AND name = ?", contributor, name).
		Find(&word)
	// 2
	if word.ID <= 0 {
		word.Contributor = contributor
		word.Name = name
		word.Value = 1
		db.Create(&word)
	} else {
		word.Value++
		db.Model(&word).
			Update("value", word.Value)
	}
	AddWordPoint(time, word)
}
