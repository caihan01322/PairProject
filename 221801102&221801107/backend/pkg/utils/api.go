package utils

import (
	"backend/pkg/e"
	"github.com/gin-gonic/gin"
	"net/http"
)

func JSONOK(c *gin.Context, code int, msg interface{}, data interface{}) {
	JSON(c, http.StatusOK, code, msg, data)
}

func JSON(c *gin.Context, statusCode int, code int, msg interface{}, data interface{}) {
	if msg == nil {
		msg = e.GetMsg(code)
	}
	if data == nil {
		data = make(map[string]interface{})
	}
	c.JSON(statusCode, gin.H{
		"code": code,
		"msg":  msg,
		"data": data,
	})
}
