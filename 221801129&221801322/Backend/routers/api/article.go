package api

import (
	"Backend/models"
	"Backend/pkg/err"
	"Backend/pkg/setting"
	"github.com/astaxie/beego/validation"
	"github.com/gin-gonic/gin"
	"log"
	"math"
	"net/http"
)

type SearchJson struct {
	PageNum   int    `json:"pagenum"`
	Type      int    `json:"type"`
	Searchval string `json:"searchval"`
}

type MarkJson struct {
	UserID  int `json:"user_id"`
	PageNum int `json:"pagenum"`
}

//展示论文列表
func ShowArticles(c *gin.Context) {
	data := make(map[string]interface{})
	temp := SearchJson{}
	var total int

	code := err.INVALID_PARAMS
	if c.ShouldBindJSON(&temp) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.GetMsg(code),
			"data": data,
		})
	}

	valid := validation.Validation{}
	valid.Required(temp.PageNum, "pagenum").Message("页码不能为空")
	valid.Required(temp.Type, "type").Message("搜索类型不能为空")
	valid.Required(temp.Searchval, "searchval").Message("搜索内容不能为null")

	valid.Min(temp.PageNum, 1, "pagenum").Message("页码最小值为1")
	valid.Range(temp.Type, 1, 3, "searchval").Message("搜索类型范围为1-3")

	if !valid.HasErrors() {
		code = err.SUCCESS
		if temp.Type == err.TITLE {
			data["articlelist"], total = models.GetArticleByTitle(temp.Searchval, (temp.PageNum-1)*setting.PageSize, setting.PageSize)
		}
		if temp.Type == err.KEYWORD {
			data["articlelist"], total = models.GetArticleByKeywords(temp.Searchval, (temp.PageNum-1)*setting.PageSize, setting.PageSize)
		}
		if temp.Type == err.ARTICLEID {
			data["articlelist"], total = models.GetArticleByArticleID(temp.Searchval, (temp.PageNum-1)*setting.PageSize, setting.PageSize)
		}
		data["pagetotal"] = int(math.Ceil(float64(total) / float64(setting.PageSize)))
	} else {
		for _, err := range valid.Errors {
			log.Printf("err.key: %s, err.message: %s", err.Key, err.Message)
		}
	}

	c.JSON(http.StatusOK, gin.H{
		"code": code,
		"msg":  err.GetMsg(code),
		"data": data,
	})
}

//展示收藏论文列表
func ShowMarkArticles(c *gin.Context) {
	data := make(map[string]interface{})
	temp := MarkJson{}
	var total int

	code := err.INVALID_PARAMS
	if c.ShouldBindJSON(&temp) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.GetMsg(code),
			"data": data,
		})
	}

	valid := validation.Validation{}
	valid.Required(temp.UserID, "user_id").Message("用户ID不能为空")
	valid.Required(temp.PageNum, "pagenum").Message("页码不能为空")
	valid.Min(temp.UserID, 1, "user_id").Message("用户ID需要大于1")
	valid.Min(temp.PageNum, 1, "pagenum").Message("页码最小值为1")

	if !valid.HasErrors() {
		if models.ExistMarkArticle(temp.UserID) {
			data["articlelist"], total = models.GetArticleByMark(temp.UserID, (temp.PageNum-1)*setting.PageSize, setting.PageSize)
			data["pagetotal"] = int(math.Ceil(float64(total) / float64(setting.PageSize)))
			code = err.SUCCESS
		} else {
			code = err.ERROR_NOT_EXIST_MARK
		}
	} else {
		for _, err := range valid.Errors {
			log.Printf("err.key: %s, err.message: %s", err.Key, err.Message)
		}
	}

	c.JSON(http.StatusOK, gin.H{
		"code": code,
		"msg":  err.GetMsg(code),
		"data": data,
	})
}
