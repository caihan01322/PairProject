package models

import "gorm.io/gorm"

type UserFav struct {
	Model
	UserID  uint   `gorm:"primaryKey" json:"-"`
	PaperID uint   `gorm:"primaryKey" json:"-"`
	Title   string `json:"title"`
	Content string `json:"content"`
}

func AddUserFav(user User, papers ...Paper) {
	db.
		Model(&user).
		Association("Papers").
		Append(papers)
}

func GetUserFav(user *User) (papers []Paper) {
	papers = []Paper{}
	db.Model(user).
		Association("Papers").
		Find(&papers)
	tempFav := &UserFav{}
	for i := range papers {
		db.Find(tempFav, "user_id = ? AND paper_id = ?", user.ID, papers[i].ID)
		papers[i].Status = 1
		papers[i].Title = tempFav.Title
		papers[i].Content = tempFav.Content
	}
	return
}

func (userFav *UserFav) AfterCreate(db *gorm.DB) (err error) {
	paper := Paper{}
	db.Find(&paper, userFav.PaperID)
	db.Model(&userFav).
		Updates(UserFav{Title: paper.Title, Content: paper.Content})
	return nil
}
