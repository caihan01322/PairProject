package api

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/utils"
	"context"
	"encoding/base64"
	"github.com/gin-gonic/gin"
	"github.com/google/go-github/v33/github"
	"github.com/mitchellh/mapstructure"
	"golang.org/x/oauth2"
	"log"
	"math/rand"
	"net/http"
)

/***************** test ************/
func Home(c *gin.Context) {
	sess, err := conf.Store.Get(c.Request, conf.AuthSessKey)
	if err != nil {
		log.Println(err)
		return
	}

	renderData := gin.H{}
	// check login state
	if accessToken, ok := sess.Values["githubAccessToken"].(*oauth2.Token); ok {
		client := github.NewClient(conf.OAuthCfg.Client(context.Background(), accessToken))

		user, _, err := client.Users.Get(context.Background(), "")
		if err != nil {
			log.Println(err)
			return
		}

		renderData["github_user"] = user

		var userMap map[string]interface{}
		if err = mapstructure.Decode(user, &userMap); err != nil {
			log.Println("error decode userMap " + err.Error())
		}
		renderData["github_user_map"] = userMap
	}

	c.HTML(http.StatusOK, "base", renderData)
}

func Auth(c *gin.Context) {
	b := make([]byte, 15)
	rand.Read(b)

	state := base64.URLEncoding.EncodeToString(b)

	sess, _ := conf.Store.Get(c.Request, conf.AuthSessKey)
	sess.Values["state"] = state
	if err := sess.Save(c.Request, c.Writer); err != nil {
		log.Println("error saving state " + err.Error())
	}

	url := conf.OAuthCfg.AuthCodeURL(state)
	c.Redirect(302, url)
}

func Callback(c *gin.Context) {}

/***************** test ************/

// @Summary 退出登录
// @Produce  json
// @Success 200 {object} swag.resEmptyObj
// @Router /logout [get]
func Logout(c *gin.Context) {
	session, _ := conf.Store.Get(c.Request, conf.AuthSessKey)

	// delete login info
	session.Options.MaxAge = -1
	session.Save(c.Request, c.Writer)

	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), nil)
}

// @Summary 登录
// @Produce  json
// @Param code query string true "OAuth Code for AccessToken"
// @Success 200 {object} swag.resLogin
// @Router /login [get]
func Login(c *gin.Context) {
	code := http.StatusInternalServerError

	authCode := c.Query("code")
	token, err := conf.OAuthCfg.Exchange(oauth2.NoContext, authCode)
	log.Println(authCode)
	if err != nil {
		log.Printf("there was an issue getting your token: %v\n", err)
	} else if !token.Valid() {
		log.Println("invalid token")
	} else {
		client := github.NewClient(conf.OAuthCfg.Client(context.Background(), token))
		user, _, err := client.Users.Get(context.Background(), "")

		if err != nil {
			log.Printf("error getting user: %v\n", err)
		} else {
			code = http.StatusOK

			// conf.Store user
			u := models.GetUser(*user.ID)
			if u == nil {
				u = &models.User{
					Name:     *user.Login,
					Avatar:   *user.AvatarURL,
					GitHubID: *user.ID,
				}
				models.AddUser(u)
			}

			// for auto login
			session, _ := conf.Store.Get(c.Request, conf.AuthSessKey)
			session.Values["githubAccessToken"] = token
			session.Values["githubID"] = *user.ID
			session.Save(c.Request, c.Writer)

			utils.JSONOK(c, code, http.StatusText(code), *u)
			return
		}
	}
	utils.JSONOK(c, code, http.StatusText(code), nil)
}
