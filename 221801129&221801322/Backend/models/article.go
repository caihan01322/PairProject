package models

type Article struct {
	Model
	Title     string    `json:"title"`
	Abstract  string    `json:"abstract"`
	ArticleID string    `json:"article_id"`
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
	article.ArticleID = data["article_id"].(string)
	article.Link = data["link"].(string)
	article.Keywords = data["keywords"].([]Keyword)
	db.Create(&article)
	return true
}

//根据论文标题搜索
func GetArticleByTitle(title string, pageNum, pageSize int) (articles []Article) {
	db.Preload("Keywords").Where("title LIKE ?", "%"+title+"%").Offset(pageNum).Limit(pageSize).Find(&articles)
	return
}

//根据论文编号搜索
func GetArticleByArticleID(articleid string, pageNum, pageSize int) (articles []Article) {
	db.Preload("Keywords").Where("article_id LIKE ?", "%"+articleid+"%").Offset(pageNum).Limit(pageSize).Find(&articles)
	return
}

//根据关键词搜索
func GetArticleByKeywords(keyword string, pageNum, pageSize int) (articles []Article) {
	db.Preload("Keywords").Raw("select * from crawler_article where crawler_article.id in " +
		"(select article_id from crawler_keyword where name like '%" + keyword + "%')").Offset(pageNum).Limit(pageSize).Find(&articles)
	return
}
