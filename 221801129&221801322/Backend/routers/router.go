package routers

import (
	middlewares "Backend/middleware"
	"Backend/routers/api"
	"github.com/gin-gonic/gin"

	"Backend/pkg/setting"
)

func InitRouter() *gin.Engine {
	router := gin.New()

	router.Use(gin.Logger())

	router.Use(gin.Recovery())

	gin.SetMode(setting.RunMode)

	apiv1 := router.Group("")
	apiv1.Use(middlewares.Cors())
	{
		//用户登录
		apiv1.POST("/login", api.LoginUser)

		//展示TOP10
		apiv1.POST("/rank", api.GetTopKeywordList)

		//展示图表
		apiv1.POST("/trend", api.GetChart)

		//显示论文列表
		apiv1.POST("/list", api.ShowArticles)

		//显示收藏列表
		apiv1.POST("/showmark", api.ShowMarkArticles)

		//收藏文章
		apiv1.POST("/mark", api.MarkArticle)

		//取消收藏文章
		apiv1.POST("/dismark", api.DisMarkArticle)
	}
	return router
}
