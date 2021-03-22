package utils

import (
	"backend/conf"
	"github.com/gin-gonic/gin"
	"github.com/unknwon/com"
)

func GetPage(c *gin.Context) (offset, pageSize int) {
	result := 0
	page := com.StrTo(c.Query("p")).MustInt()
	if page > 0 {
		result = (page - 1) * conf.PageSize
	}
	return result, conf.PageSize
}
