package models

type Article struct {
	Model
	Title     string    `json:"title"`
	Abstract  string    `json:"abstract"`
	ArticleId string    `json:"article_id"`
	Link      string    `json:"link"`
	Keywords  []Keyword `gorm:"foreignKey:article_id"`
}

//添加文章
func AddArticle(data map[string]interface{}) bool {
	article := Article{}
	article.Title = data["title"].(string)
	if data["abstract"] != nil {
		article.Abstract = data["abstract"].(string)
	}
	article.ArticleId = data["article_id"].(string)
	article.Link = data["link"].(string)
	article.Keywords = data["keywords"].([]Keyword)
	db.Create(&article)
	return true
}
