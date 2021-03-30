package routers

import (
	"Backend/routers/api"
	"github.com/gin-gonic/gin"

	"Backend/pkg/setting"
)

func InitRouter() *gin.Engine {
	router := gin.New()

	router.Use(gin.Logger())

	router.Use(gin.Recovery())

	gin.SetMode(setting.RunMode)

	apiv1 := router.Group("/home")
	{
		//用户登录
		apiv1.POST("", api.LoginUser)

	}

	return router
}
