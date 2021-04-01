package main

import (
	"backend/conf"
	_ "backend/docs"
	_ "backend/models" // initialize database
	"backend/routers"
	"fmt"
	ginSwagger "github.com/swaggo/gin-swagger"
	"github.com/swaggo/gin-swagger/swaggerFiles"
	"log"
	"net/http"
)

// @title 顶会热词统计
// @version 1.0
// @description 顶会热词统计API快速测试页面

// @license.name Apache 2.0
// @license.url http://www.apache.org/licenses/LICENSE-2.0.html

// @contact.name 网站主页
// @contact.url http://pairproject.nosae.icu

// @host pairproject.nosae.icu
// @BasePath /api/v1/

// @securitydefinitions.oauth2.accessCode OAuth2AccessCode
// @tokenUrl https://github.com/login/oauth/access_token
// @authorizationurl https://github.com/login/oauth/authorize
// @scope.user
// @scope.repo
func main() {
	StartCrawler()
	// crawler.Start()

	r := routers.InitRouter()
	// api-doc
	// swagConfig := &ginSwagger.Config{
	// 	URL: "http://localhost:8000/api/v1/swagger/doc.json",
	// }
	r.GET("/api/v1/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))

	s := http.Server{
		Addr:           fmt.Sprintf(":%d", conf.HTTPPort),
		Handler:        r, // cors
		ReadTimeout:    conf.ReadTimeOut,
		WriteTimeout:   conf.WriteTimeOut,
		MaxHeaderBytes: 1 << 20,
	}

	if err := s.ListenAndServe(); err != nil {
		log.Fatalln(err)
	}

}
