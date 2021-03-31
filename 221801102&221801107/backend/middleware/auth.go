package middleware

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

func Auth() gin.HandlerFunc {
	return func(c *gin.Context) {
		sess, _ := conf.Store.Get(c.Request, conf.AuthSessKey)

		if _, ok := sess.Values["githubAccessToken"]; ok {
			id := sess.Values["githubID"].(int64)
			user := models.GetUser(id)
			c.Set("user", user)
			c.Next()
		} else {
			utils.JSONUnauthorized(c, http.StatusUnauthorized, http.StatusText(http.StatusUnauthorized), nil)
			c.Abort()
		}
	}
}
