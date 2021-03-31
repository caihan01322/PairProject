package models

type User struct {
	Model
	Name     string `json:"name"`
	Avatar   string `json:"avatar"`
	GitHubID int64  `json:"-" gorm:"uniqueIndex;column:github_id"`

	Papers []Paper `gorm:"many2many:user_fav;constraint:OnDelete:CASCADE;" json:"-"`
}

func GetUser(gitHubID int64) (user *User) {
	user = &User{}
	db.Find(user, "github_id = ?", gitHubID)
	if user.ID > 0 {
		return
	} else {
		return nil
	}
}

func AddUser(user *User) {
	db.Create(&user)
}
