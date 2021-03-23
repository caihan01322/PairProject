package api

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/e"
	"backend/pkg/utils"
	"context"
	"encoding/base64"
	"encoding/gob"
	"github.com/gin-gonic/gin"
	"github.com/google/go-github/v33/github"
	"github.com/gorilla/sessions"
	"github.com/mitchellh/mapstructure"
	"golang.org/x/oauth2"
	"log"
	"math/rand"
	"net/http"
)

var store *sessions.CookieStore

const authSessKey = "auth_ses"

func init() {
	store = sessions.NewCookieStore([]byte(conf.ServerSecret))
	gob.Register(&oauth2.Token{})
}

/***************** test ************/
func Home(c *gin.Context) {
	sess, err := store.Get(c.Request, authSessKey)
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

	sess, _ := store.Get(c.Request, authSessKey)
	sess.Values["state"] = state
	if err := sess.Save(c.Request, c.Writer); err != nil {
		log.Println("error saving state " + err.Error())
	}

	url := conf.OAuthCfg.AuthCodeURL(state)
	c.Redirect(302, url)
}

func Callback(c *gin.Context) {}

/***************** test ************/

func Logout(c *gin.Context) {
	session, _ := store.Get(c.Request, authSessKey)

	// delete login info
	session.Options.MaxAge = -1
	session.Save(c.Request, c.Writer)

	utils.JSONOK(c, e.Success, e.GetMsg(e.Success), nil)
}

func Login(c *gin.Context) {
	code := e.Error

	authCode := c.Query("code")
	token, err := conf.OAuthCfg.Exchange(context.Background(), authCode)
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
			code = e.Success

			// store user
			u := models.GetUser(*user.ID)
			if u != nil {
				models.LoginUser = *u
			} else {
				models.LoginUser = models.User{
					Name:     *user.Name,
					Avatar:   *user.AvatarURL,
					GitHubID: *user.ID,
				}
				models.AddUser(&models.LoginUser)
			}

			// for auto login
			session, _ := store.Get(c.Request, authSessKey)
			session.Values["githubAccessToken"] = token
			session.Save(c.Request, c.Writer)
		}
	}

	utils.JSONOK(c, code, e.GetMsg(code), models.LoginUser)
}
