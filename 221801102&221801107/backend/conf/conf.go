package conf

import (
	"encoding/gob"
	"github.com/gorilla/sessions"
	"golang.org/x/oauth2"
	"gopkg.in/ini.v1"
	"log"
	"time"
)

var (
	Cfg          *ini.File
	OAuthCfg     *oauth2.Config
	ServerSecret string

	RunMode string // gin run mode

	HTTPPort     int
	ReadTimeOut  time.Duration
	WriteTimeOut time.Duration

	PageSize int // pagination

	scopes = []string{"repo"}

	Store *sessions.CookieStore
)

const (
	githubAuthUrl  = "https://github.com/login/oauth/authorize"
	githubTokenUrl = "https://github.com/login/oauth/access_token"
	redirectUrl    = "http://localhost:8000/auth-callback"
	AuthSessKey    = "auth_ses"
)

func init() {
	var err error
	Cfg, err = ini.Load("conf/conf.ini")
	if err != nil {
		log.Fatalf("Fail to parse 'conf/conf.ini': %v", err)
	}
	// read only
	Cfg.BlockMode = false

	LoadServer()
	LoadApp()
	LoadOAuth()

	Store = sessions.NewCookieStore([]byte(ServerSecret))
	gob.Register(&oauth2.Token{})
}

func LoadOAuth() {
	sec, err := Cfg.GetSection("oauth")
	if err != nil {
		log.Fatalf("Fail to get section 'oauth': %v", err)
	}
	OAuthCfg = &oauth2.Config{
		ClientID:     sec.Key("client_id").String(),
		ClientSecret: sec.Key("client_secret").String(),
		Endpoint: oauth2.Endpoint{
			AuthURL:  githubAuthUrl,
			TokenURL: githubTokenUrl,
		},
		RedirectURL: redirectUrl,
		Scopes:      scopes,
	}
}

func LoadApp() {
	sec, err := Cfg.GetSection("app")
	if err != nil {
		log.Fatalf("Fail to get section 'app': %v", err)
	}

	RunMode = Cfg.Section("").Key("run_mode").MustString("debug")
	PageSize = sec.Key("page_size").MustInt(10)
}

func LoadServer() {
	sec, err := Cfg.GetSection("server")
	if err != nil {
		log.Fatalf("Fail to get section 'server': %v", err)
	}
	HTTPPort = sec.Key("http_port").MustInt(80)
	ReadTimeOut = time.Duration(sec.Key("read_timeout").MustInt(60)) * time.Second
	WriteTimeOut = time.Duration(sec.Key("read_timeout").MustInt(60)) * time.Second
	ServerSecret = sec.Key("server_secret").String()
}
