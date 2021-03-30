package api

import (
	"Backend/models"
	"Backend/pkg/err"
	"github.com/astaxie/beego/validation"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

//用户登录
func LoginUser(c *gin.Context) {
	var data models.User
	json := models.User{}
	code := err.INVALID_PARAMS

	if c.ShouldBindJSON(&json) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.MsgFlags[code],
			"data": data,
		})
		return
	}

	valid := validation.Validation{}
	valid.Required(json.Username, "username").Message("用户名不能为空")
	valid.Required(json.Password, "password").Message("密码不能为空")

	if !valid.HasErrors() {
		if models.ExistUserByPhoneNumber(json.Username) {
			if models.CheckPassword(json.Username, json.Password) {
				code = err.SUCCESS
				data = models.GetUserByPhonenumber(json.Username)
				data.Password = ""
			} else {
				code = err.ERROR_INVALID_PASSWORD
			}
		} else {
			code = err.ERROR_NOT_EXIST_USER
		}
	} else {
		for _, err := range valid.Errors {
			log.Printf("err.key: %s, err.message: %s", err.Key, err.Message)
		}
	}

	c.JSON(http.StatusOK, gin.H{
		"code": code,
		"msg":  err.MsgFlags[code],
		"data": data,
	})

}
