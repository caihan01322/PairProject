package main

import (
	"221801102/conf"
	_ "221801102/models" // initialize database
	"221801102/routers"
	"fmt"
	"log"
	"net/http"
)

func main() {
	// prepare templates

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
