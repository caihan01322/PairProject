package models

// all paper
type Paper struct {
	Model
	Title       string `json:"title"`
	Contributor string `json:"contributor"`
	Code        string `json:"code"`
	Content     string `json:"content"`
	Link        string `json:"link"`

	Status int `json:"status" gorm:"-"`
}

func AddPaper(paper *Paper) {
	db.Create(&paper)
}
