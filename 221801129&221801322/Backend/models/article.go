package models

type Article struct {
	Model
	Title     string `json:"title"`
	Abstract  string `json:"abstract"`
	ArticleId string `json:"article_id"`
	Link      string `json:"link"`
}

//添加文章
func AddArticle(data map[string]interface{}) bool {
	db.Create(&Article{
		Title:     data["title"].(string),
		Abstract:  data["abstract"].(string),
		ArticleId: data["article_id"].(string),
		Link:      data["link"].(string),
	})
	return true
}
