package routers

import (
	"backend/conf"
	"backend/routers/api"
	"github.com/gin-gonic/gin"
)

func InitRouter() *gin.Engine {
	r := gin.New()
	r.LoadHTMLGlob("templates/*")
	r.Use(gin.Logger())
	r.Use(gin.Recovery())
	gin.SetMode(conf.RunMode)

	r.GET("/", api.Home)
	r.GET("/auth", api.Auth)
	r.GET("/auth-callback", api.Callback)
	r.GET("/logout", api.Logout)
	return r
}
