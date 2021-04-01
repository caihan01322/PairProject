package main

import (
	"backend/crawler"
	"github.com/robfig/cron/v3"
	"log"
)

func StartCrawler() {
	log.Println("Pending to scrape ieee papers...")

	c := cron.New(cron.WithParser(
		cron.NewParser(
			cron.SecondOptional | cron.Minute | cron.Hour | cron.Dom | cron.Month | cron.Dow),
	),
	)
	// run at every Sunday midnight
	_, err := c.AddFunc("0 0 0 * * ?", func() {
		crawler.Start()
	})
	if err != nil {
		log.Println(err)
	}
	c.Start()
}
