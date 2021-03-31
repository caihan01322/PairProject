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
		for _, e := range valid.Errors {
			log.Printf("err.key: %s, err.message: %s", e.Key, e.Message)
		}
	}

	c.JSON(http.StatusOK, gin.H{
		"code": code,
		"msg":  err.MsgFlags[code],
		"data": data,
	})

}

//用户收藏文章
func MarkArticle(c *gin.Context) {
	temp := models.Bookmark{}
	code := err.INVALID_PARAMS

	if c.ShouldBindJSON(&temp) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.GetMsg(code),
			"data": make(map[string]interface{}),
		})
		return
	}

	valid := validation.Validation{}
	valid.Required(temp.UserID, "user_id").Message("用户ID不能为空")
	valid.Required(temp.PaperID, "paper_id").Message("文章ID不能为空")
	valid.Min(temp.UserID, 1, "user_id").Message("用户ID最小值为1")
	valid.Min(temp.PaperID, 1, "paper_id").Message("文章ID最小值为1")

	if !valid.HasErrors() {
		if !models.ExistMark(temp.UserID, temp.PaperID) {
			models.MarkArticle(temp.UserID, temp.PaperID)
			code = err.SUCCESS
		} else {
			code = err.ERROR_EXIST_MARK
		}
	} else {
		for _, e := range valid.Errors {
			log.Printf("err.key: %s, err.message: %s", e.Key, e.Message)
		}
	}

	c.JSON(http.StatusOK, gin.H{
		"code": code,
		"msg":  err.GetMsg(code),
		"data": make(map[string]interface{}),
	})
}

//用户取消收藏文章
func DisMarkArticle(c *gin.Context) {
	temp := models.Bookmark{}
	code := err.INVALID_PARAMS

	if c.ShouldBindJSON(&temp) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.GetMsg(code),
			"data": make(map[string]interface{}),
		})
		return
	}

	valid := validation.Validation{}
	valid.Required(temp.UserID, "user_id").Message("用户ID不能为空")
	valid.Required(temp.PaperID, "paper_id").Message("文章ID不能为空")
	valid.Min(temp.UserID, 1, "user_id").Message("用户ID最小值为1")
	valid.Min(temp.PaperID, 1, "paper_id").Message("文章ID最小值为1")

	if !valid.HasErrors() {
		if models.ExistMark(temp.UserID, temp.PaperID) {
			models.DisMarkArticle(temp.UserID, temp.PaperID)
			code = err.SUCCESS
		} else {
			code = err.ERROR_NOT_EXIST_MARK
		}
	} else {
		for _, e := range valid.Errors {
			log.Printf("err.key: %s, err.message: %s", e.Key, e.Message)
		}
	}

	c.JSON(http.StatusOK, gin.H{
		"code": code,
		"msg":  err.GetMsg(code),
		"data": make(map[string]interface{}),
	})
}
