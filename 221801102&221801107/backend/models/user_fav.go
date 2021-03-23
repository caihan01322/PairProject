package models

import "gorm.io/gorm"

type UserFav struct {
	Model
	UserID  uint   `gorm:"primaryKey" json:"-"`
	PaperID uint   `gorm:"primaryKey" json:"-"`
	Title   string `json:"title"`
	Content string `json:"content"`
}

func AddUserFav(user *User, paper *Paper) {
	db.Model(user).
		Association("Papers").
		Append(paper)
}

func DeleteUserFav(fav *UserFav) {
	db.Delete(fav)
}

func GetUserFav(user *User, paper *Paper) (fav UserFav) {
	db.Where("user_id = ? AND paper_id = ?", user.ID, paper.ID).
		Find(&fav)
	return
}

func GetUserFavs(user *User, offset, pageSize int) (papers []Paper, total int64) {
	papers = []Paper{}
	db.Model(user).
		Offset(offset).
		Limit(pageSize).
		Association("Papers").
		Find(&papers)
	db.Model(&UserFav{}).
		Where("user_id = ?", user.ID).
		Count(&total)
	tempFav := &UserFav{}
	for i := range papers {
		db.Find(tempFav, "user_id = ? AND paper_id = ?", user.ID, papers[i].ID)
		papers[i].Status = 1
		papers[i].Title = tempFav.Title
		papers[i].Content = tempFav.Content
	}
	return
}

func EditUserFav(fav *UserFav) {
	db.Save(fav)
}

func (userFav *UserFav) AfterCreate(db *gorm.DB) (err error) {
	paper := Paper{}
	db.Find(&paper, userFav.PaperID)
	db.Model(&userFav).
		Updates(UserFav{Title: paper.Title, Content: paper.Content})
	return nil
}
