package middleware

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"golang.org/x/oauth2"
	"net/http"
)

func Auth() gin.HandlerFunc {
	return func(c *gin.Context) {
		sess, _ := conf.Store.Get(c.Request, conf.AuthSessKey)

		if _, ok := sess.Values["githubAccessToken"].(*oauth2.Token); ok {
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
