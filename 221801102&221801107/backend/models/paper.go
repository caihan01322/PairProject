package models

import "gorm.io/gorm"

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

func IsPaperExist(code string) bool {
	return db.Where("code = ?", code).Find(&Paper{}).Error == nil
}

func GetPaperByCode(code string) (paper Paper) {
	db.Find(&paper, "code = ?", code)
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
