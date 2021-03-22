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

func AddPaper(data map[string]interface{}) {
	paper := &Paper{
		Title:       data["title"].(string),
		Contributor: data["contributor"].(string),
		Code:        data["code"].(string),
		Content:     data["content"].(string),
		Link:        data["link"].(string),
	}

	db.Create(&paper)
}
