package models

type User struct {
	Model
	PhoneNumber string `json:"phonenumber"`
	Password    string `json:"password"`
}

func GetUserByPhonenumber(phonenumber string) (user User) {
	db.Where("phone_number = ?", phonenumber).First(&user)
	return
}

func ExistUserByPhoneNumber(phonenumber string) bool {
	var user User
	db.Select("id").Where("phone_number = ?", phonenumber).First(&user)
	if user.ID > 0 {
		return true
	}

	return false
}

func CheckPassword(phonenumber, password string) bool {
	var user User
	db.Where(User{PhoneNumber: phonenumber, Password: password}).First(&user)

	if user.ID > 0 {
		return true
	}

	return false
}
