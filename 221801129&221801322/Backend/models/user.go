package models

type User struct {
	Model
	Username string `json:"username"`
	Password string `json:"password"`
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
