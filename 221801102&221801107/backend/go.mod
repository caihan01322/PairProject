module backend

go 1.14

require (
	// HTTP
	github.com/gin-gonic/gin v1.6.3
	github.com/go-playground/validator/v10 v10.4.1 // indirect
	github.com/go-redis/redis v6.15.9+incompatible
	github.com/gocolly/colly v1.2.0
	// crawler
	github.com/gocolly/colly/v2 v2.1.0
	github.com/gocolly/redisstorage v0.0.0-20190812112800-1745c5e6d0ba
	github.com/golang/protobuf v1.5.1 // indirect
	// github api
	github.com/google/go-github/v33 v33.0.0
	// session
	github.com/gorilla/sessions v1.2.1
	github.com/json-iterator/go v1.1.10
	github.com/leodido/go-urn v1.2.1 // indirect
	// struct 2 map
	github.com/mitchellh/mapstructure v1.4.1
	github.com/modern-go/concurrent v0.0.0-20180306012644-bacd9c7ef1dd // indirect
	github.com/modern-go/reflect2 v1.0.1 // indirect
	// cron
	github.com/robfig/cron/v3 v3.0.0
	github.com/ugorji/go v1.2.4 // indirect
	// utils
	github.com/unknwon/com v1.0.1
	golang.org/x/crypto v0.0.0-20210317152858-513c2a44f670 // indirect
	golang.org/x/net v0.0.0-20210316092652-d523dce5a7f4 // indirect
	// oauth2
	golang.org/x/oauth2 v0.0.0-20210313182246-cd4f82c27b84
	golang.org/x/sys v0.0.0-20210320140829-1e4c9ba3b0c4 // indirect
	google.golang.org/appengine v1.6.7 // indirect
	// config
	gopkg.in/ini.v1 v1.62.0
	gopkg.in/yaml.v2 v2.4.0 // indirect
	// mysql driver
	gorm.io/driver/mysql v1.0.5
	// orm
	gorm.io/gorm v1.21.4
)
