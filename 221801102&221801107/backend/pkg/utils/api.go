package utils

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func JSONOK(c *gin.Context, code int, msg interface{}, data interface{}) {
	JSON(c, http.StatusOK, code, msg, data)
}

func JSONUnauthorized(c *gin.Context, code int, msg interface{}, data interface{}) {
	JSON(c, http.StatusUnauthorized, code, msg, data)
}

func JSON(c *gin.Context, statusCode int, code int, msg interface{}, data interface{}) {
	if msg == nil {
		msg = http.StatusText(code)
	}
	if data == nil {
		data = make(map[string]interface{})
	}
	// TODO use JSON in release version
	c.IndentedJSON(statusCode, gin.H{
		"code": code,
		"msg":  msg,
		"data": data,
	})
}
