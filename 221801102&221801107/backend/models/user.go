package models

type User struct {
	Model
	Name     string `json:"name"`
	Avatar   string `json:"avatar"`
	GitHubID int64  `json:"-" gorm:"uniqueIndex"`

	Papers []Paper `gorm:"many2many:user_fav;constraint:OnDelete:CASCADE;" json:"-"`
}

func AddUser(data map[string]interface{}) {
	user := User{
		Name:     data["name"].(string),
		Avatar:   data["avatar"].(string),
		GitHubID: data["GitHubID"].(int64),
	}
	db.Create(&user)
}

func GetUser() (user *User) {
	db.First(&user)
	return
}

func DeleteUser() {
	user := User{}
	db.Find(&user)
	db.Delete(&user)
}
