package conf

import (
	"gopkg.in/ini.v1"
	"log"
	"time"
)

var (
	Cfg *ini.File

	RunMode string // gin run mode

	HTTPPort     int
	ReadTimeOut  time.Duration
	WriteTimeOut time.Duration

	PageSize int // pagination
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
}
