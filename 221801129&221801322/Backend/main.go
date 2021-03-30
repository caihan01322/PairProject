package main

import (
	"Backend/models"
	"Backend/pkg/setting"
	"Backend/routers"
	"fmt"
	"net/http"
)

func main() {
	router := routers.InitRouter()

	s := http.Server{
		Addr:           fmt.Sprintf(":%d", setting.HTTPPort),
		Handler:        router,
		ReadTimeout:    setting.ReadTimeout,
		WriteTimeout:   setting.WriteTimeout,
		MaxHeaderBytes: 1 << 20,
	}

	_ = s.ListenAndServe()

	models.GetChart("ECCV", models.GetTOPByForum("ECCV", 5))

}
