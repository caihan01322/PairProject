package api

import (
	"Backend/models"
	"Backend/pkg/err"
	"github.com/astaxie/beego/validation"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

type Type struct {
	Type string `json:"type"`
}

//返回TOP10
func GetTopKeywordList(c *gin.Context) {
	data := make(map[string]interface{})
	temp := Type{}
	code := err.INVALID_PARAMS

	if c.ShouldBindJSON(&temp) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.GetMsg(code),
			"data": data,
		})
		return
	}

	valid := validation.Validation{}
	valid.Required(temp.Type, "type").Message("type不能为空")

	if !valid.HasErrors() {
		code = err.SUCCESS
		data["top_list"] = models.GetTopKeywordList()
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

//返回图表
func GetChart(c *gin.Context) {
	data := make(map[string]interface{})
	var charts []models.Chart
	temp := Type{}
	code := err.INVALID_PARAMS

	if c.ShouldBindJSON(&temp) != nil {
		c.JSON(http.StatusOK, gin.H{
			"code": code,
			"msg":  err.GetMsg(code),
			"data": data,
		})
		return
	}

	valid := validation.Validation{}
	valid.Required(temp.Type, "type").Message("type不能为空")

	if !valid.HasErrors() {
		code = err.SUCCESS

		charts = append(charts, models.GetChart("CVPR", models.GetTOPByForum("CVPR", 4)))
		charts = append(charts, models.GetChart("ECCV", models.GetTOPByForum("ECCV", 4)))
		charts = append(charts, models.GetChart("ICCV", models.GetTOPByForum("ICCV", 4)))

		data["trend"] = charts
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
