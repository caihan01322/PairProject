package routers

import (
	"backend/conf"
	"backend/middleware"
	"backend/routers/api"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"time"
)

func InitRouter() *gin.Engine {
	r := gin.New()
	r.LoadHTMLGlob("templates/*")
	r.Use(gin.Logger())
	r.Use(gin.Recovery())
	r.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"*"},
		AllowHeaders:     []string{"Origin", "Content-Length", "Content-Type", "Cookies"},
		AllowMethods:     []string{"GET", "POST", "PUT", "PATCH", "DELETE", "HEAD"},
		AllowCredentials: true,
		MaxAge:           12 * time.Hour,
	}))
	// corsWare := ginCors.New(cors.Options{
	// 	AllowedOrigins: []string{"*"},
	// 	AllowedMethods: []string{
	// 		http.MethodHead,
	// 		http.MethodGet,
	// 		http.MethodPost,
	// 		http.MethodPut,
	// 		http.MethodPatch,
	// 		http.MethodDelete,
	// 	},
	// 	AllowedHeaders:   []string{"*"},
	// 	AllowCredentials: true,
	// 	Debug: true,
	// })
	// r.Use(corsWare)
	gin.SetMode(conf.RunMode)

	// r.GET("/", api.Home)
	apiV1 := r.Group("/api/v1")
	{

		apiV1.GET("/auth", api.Auth)
		apiV1.GET("/auth-callback", api.Callback)

		apiV1.GET("/login", api.Login)

		authGroup := apiV1.Group("/").Use(middleware.Auth())
		{

			cors.Default()
			authGroup.GET("/logout", api.Logout)
			authGroup.POST("/search", api.Search)
			authGroup.GET("/fav", api.GetUserFav)
			authGroup.GET("/op-fav", api.OpUserFav)
			authGroup.POST("/ed-fav", api.EditUserFav)
			authGroup.GET("/cloud", api.GetWordCloud)
			authGroup.GET("/words", api.GetWords)
		}
	}

	return r
}
