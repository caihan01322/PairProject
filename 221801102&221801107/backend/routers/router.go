package routers

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/e"
	"backend/pkg/utils"
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
	r.GET("/logout", api.Logout)

	r.GET("/init", Init)
	r.GET("/clear", Clear)
	r.GET("/delete-user", DeleteUser)
	r.GET("/get-fav", GetFav)
	return r
}

func GetFav(c *gin.Context) {
	user := models.GetUser()
	fav := models.GetUserFav(user)
	utils.JSONOK(c, e.Success, e.GetMsg(e.Success), fav)
}

func DeleteUser(c *gin.Context) {
	models.DeleteUser()
}

func Clear(c *gin.Context) {
	models.Clear()
}

func Init(c *gin.Context) {
	models.Init()
}
