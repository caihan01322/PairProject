package models

type Keyword struct {
	Model
	Name     string `json:"name"`
	Year     string `json:"year"`
	Quantity int    `json:"quantity"`
}

//根据名字和年份判断Key存不存在
func ExistKeywordByNameAndYear(name, year string) bool {
	var keyword Keyword
	db.Select("id").Where("name = ", name).Where("year = ", year).First(&keyword)

	if keyword.ID > 0 {
		return true
	}

	return false
}

func GetQuantityByNameAndYear(name, year string) int {
	var keyword Keyword
	db.Select("quantity").Where("name = ", name).Where("year = ", year).First(&keyword)
	return keyword.Quantity
}

func AddKeyword(name, year string) bool {
	data := make(map[string]interface{})
	if ExistKeywordByNameAndYear(name, year) {
		quantity := GetQuantityByNameAndYear(name, year)
		data["name"] = name
		data["year"] = year
		data["quantity"] = quantity + 1
		db.Model(&Keyword{}).Where("name = ?", name).Where("year = ", year).Updates(data)
	} else {
		data["name"] = name
		data["year"] = year
		data["quantity"] = 1
		db.Create(&Keyword{
			Name:     data["name"].(string),
			Year:     data["year"].(string),
			Quantity: data["quantity"].(int),
		})
	}
	return true
}
