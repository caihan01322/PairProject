package models

import (
	"gorm.io/gorm"
	"gorm.io/gorm/clause"
	"time"
)

type WordPoint struct {
	Model
	Time   time.Time `json:"time" gorm:"uniqueIndex:ui"`
	Value  int       `json:"value"`
	WordID uint      `json:"-" gorm:"uniqueIndex:ui"`
}

var addWordPointLock = make(chan int, 1)

func addWordPoint(t time.Time, word Word) {
	wordPoint := WordPoint{
		Time:   t,
		Value:  1,
		WordID: word.ID,
	}
	addWordPointLock <- 1
	// p lock for update, fail without no lock
	db.Clauses(clause.Locking{Strength: "UPDATE"}).
		Where("word_id = ? AND time = ?", word.ID, t).
		Find(&wordPoint)

	if wordPoint.ID <= 0 {
		// create, value + on conflict(update has lock itself)
		db.Clauses(clause.OnConflict{
			Columns:   []clause.Column{{Name: "word_id"}, {Name: "time"}},
			DoUpdates: clause.Assignments(map[string]interface{}{"value": gorm.Expr("value + 1")}),
		}).Create(&wordPoint)
	} else {
		// update
		db.Model(&wordPoint).Update("value", wordPoint.Value+1)
	}
	<-addWordPointLock

}
