package api

import (
	"Backend/models"
	"Backend/pkg/err"
	"github.com/gin-gonic/gin"
	"net/http"
)

func GetTopKeywordList(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{
		"code": err.SUCCESS,
		"msg":  err.GetMsg(err.SUCCESS),
		"data": models.GetTopKeywordList(),
	})
}
