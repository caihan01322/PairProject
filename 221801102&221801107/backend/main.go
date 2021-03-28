package main

import (
	"backend/conf"
	_ "backend/models" // initialize database
	"backend/routers"
	"fmt"
	"log"
	"net/http"
)

func main() {
	StartCrawler()

	r := routers.InitRouter()

	s := http.Server{
		Addr:           fmt.Sprintf(":%d", conf.HTTPPort),
		Handler:        r,
		ReadTimeout:    conf.ReadTimeOut,
		WriteTimeout:   conf.WriteTimeOut,
		MaxHeaderBytes: 1 << 20,
	}

	if err := s.ListenAndServe(); err != nil {
		log.Fatalln(err)
	}

}
