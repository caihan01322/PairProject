package api

import (
	"221801102/conf"
	"context"
	"crypto/rand"
	"encoding/base64"
	"encoding/gob"
	"github.com/gin-gonic/gin"
	"github.com/google/go-github/v33/github"
	"github.com/gorilla/sessions"
	"github.com/mitchellh/mapstructure"
	"golang.org/x/oauth2"
	"log"
	"net/http"
)

var store *sessions.CookieStore

const authSessKey = "auth_ses"

func init() {
	store = sessions.NewCookieStore([]byte(conf.ServerSecret))
	gob.Register(&oauth2.Token{})
}

// index
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

// start auth
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

// auth callback
func Callback(c *gin.Context) {
	session, err := store.Get(c.Request, authSessKey)
	if err != nil {
		log.Println(err)
		return
	}

	ss := c.Query("state")
	ss2 := session.Values["state"].(string)
	if ss != ss2 {
		log.Println("no state match; possible csrf OR cookies not enabled " + ss + " " + ss2)
		return
	}

	code := c.Query("code")
	token, err := conf.OAuthCfg.Exchange(context.Background(), code)
	if err != nil {
		log.Println("there was an issue getting your token " + err.Error())
		return
	}

	if !token.Valid() {
		log.Println("invalid token")
		return
	}

	client := github.NewClient(conf.OAuthCfg.Client(context.Background(), token))

	user, _, err := client.Users.Get(context.Background(), "")
	if err != nil {
		log.Println("error getting user")
		return
	}

	session.Values["githubUserName"] = user.Name
	session.Values["githubAccessToken"] = token
	if err = session.Save(c.Request, c.Writer); err != nil {
		log.Println("error saving token " + err.Error())
	}

	c.Redirect(http.StatusFound, "/")
}

func Logout(c *gin.Context) {
	session, err := store.Get(c.Request, authSessKey)
	if err != nil {
		log.Println("logout fail " + err.Error())
		return
	}

	// delete cookie session
	session.Options.MaxAge = -1
	if err = session.Save(c.Request, c.Writer); err != nil {
		log.Println("error saving MaxAge" + err.Error())
	}

	c.Redirect(http.StatusFound, "/")
}
