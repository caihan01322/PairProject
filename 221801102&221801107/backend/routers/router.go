package routers

import (
	"backend/conf"
	"backend/middleware"
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

	r.GET("/login", api.Login)

	authGroup := r.Group("/").Use(middleware.Auth())
	{
		authGroup.GET("/logout", api.Logout)
		authGroup.POST("/search", api.Search)
		authGroup.GET("/fav", api.GetUserFav)
		authGroup.GET("/op-fav", api.OpUserFav)
		authGroup.POST("/ed-fav", api.EditUserFav)
		authGroup.GET("/cloud", api.GetWordCloud)
		authGroup.GET("/words", api.GetWords)
	}
	return r
}
