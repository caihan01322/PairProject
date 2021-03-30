package models

import (
	"fmt"
	"gorm.io/gorm"
)

// all paper
type Paper struct {
	Model
	Title       string `json:"title"`
	Contributor string `json:"contributor"`
	Code        string `json:"code"` // id for deduplication
	Content     string `json:"content"`
	Link        string `json:"link"`

	Status int `json:"status" gorm:"-"`
}

func AddPaper(paper *Paper) {
	db.Create(&paper)
}

func GetPapersByID(ids []uint) (papers []Paper) {
	db.Find(&papers, ids)
	return
}

func GetPapersByStrID(ids []string) (papers []Paper) {
	db.Find(&papers, ids)
	return
}

func GetPaperIDsBySearch(q string) (ids []int) {
	realQ := fmt.Sprintf("%%%s%%", q)
	db.Model(&Paper{}).
		Select("id").
		Where("title like ? or content like ?", realQ, realQ).
		Find(&ids)
	return
}

func IsPaperExist(code string) bool {
	return db.Where("code = ?", code).Find(&Paper{}).Error == nil
}

func GetPaperByCode(code string) (paper *Paper) {
	paper = &Paper{}
	db.Find(&paper, "code = ?", code)
	return
}

func UpdatePaperContent(paper *Paper) {
	db.Model(paper).
		Update("content", paper.Content)
}

func GetPaperCount() (total int64) {
	db.Model(&Paper{}).
		Count(&total)
	return
}

func (paper *Paper) AfterFind(db *gorm.DB) (err error) {
	if notFound := db.Where("paper_id = ?", paper.ID).Find(&UserFav{}); notFound != nil {
		paper.Status = 0
	} else {
		paper.Status = 1
	}
	return nil
}
