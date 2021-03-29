package models

type Keyword struct {
	Model
	Name      string `json:"name"`
	Year      string `json:"year"`
	Forum     string `json:"forum"`
	ArticleID uint   `json:"article_id"`
}
