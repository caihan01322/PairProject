package models

type User struct {
	Model
	Username string `json:"username"`
	Password string `json:"password"`
}

type Bookmark struct {
	Model
	UserID  int `json:"user_id"`
	PaperID int `json:"article_id"`
}

func GetUserByPhonenumber(username string) (user User) {
	db.Where("username = ?", username).First(&user)
	return
}

func ExistUserByPhoneNumber(username string) bool {
	var user User
	db.Select("id").Where("username = ?", username).First(&user)
	if user.ID > 0 {
		return true
	}

	return false
}

func CheckPassword(username, password string) bool {
	var user User
	db.Where(User{Username: username, Password: password}).First(&user)

	if user.ID > 0 {
		return true
	}

	return false
}

func ExistMark(user_id, article_id int) bool {
	var bookmark Bookmark
	db.Table("crawler_bookmark").Where("user_id = ?", user_id).Where("paper_id = ?", article_id).First(&bookmark)
	if bookmark.ID > 0 {
		return true
	}

	return false
}

func MarkArticle(user_id, article_id int) bool {
	bookmark := Bookmark{
		UserID:  user_id,
		PaperID: article_id,
	}
	db.Table("crawler_bookmark").Create(&bookmark)

	return true
}

func DisMarkArticle(user_id, article_id int) bool {
	bookmark := Bookmark{
		UserID:  user_id,
		PaperID: article_id,
	}
	db.Table("crawler_bookmark").Delete(&bookmark)

	return true
}
