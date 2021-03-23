package models

import (
	"time"
)

type WordPoint struct {
	Model
	Time   time.Time `json:"time"`
	Value  int       `json:"value"`
	WordID uint      `json:"-"`
}

const layoutUS = "Jan. 2006"

func AddWordPoint(t string, word Word) {
	_t, _ := time.Parse(layoutUS, t)
	wordPoint := WordPoint{}
	// 3
	db.Find(&wordPoint, "word_id = ? AND time = ?", word.ID, _t)
	// 4
	if wordPoint.ID <= 0 {
		wordPoint.Time = _t
		wordPoint.Value = 1
		db.Model(&word).
			Association("Points").
			Append(&wordPoint)
	} else {
		wordPoint.Value++
		db.Model(&wordPoint).
			Update("value", wordPoint.Value)
	}
}
