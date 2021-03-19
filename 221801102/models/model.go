package models

import (
	"221801102/conf"
	"fmt"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
	"gorm.io/gorm/schema"
	"log"
	"os"
	"time"
)

var db *gorm.DB

// base model with some universal fields
type Model struct {
	ID        uint `gorm:"primarykey"`
	CreatedAt time.Time
	UpdatedAt time.Time
}

// TODO test, delete it for dev
type Tag struct {
	Model
	Name       string `json:"name"`
	CreatedBy  string `json:"created_by"`
	ModifiedBy string `json:"modified_by"`
	State      int    `json:"state"`
}

// setting and migrate database
func init() {
	sec, err := conf.Cfg.GetSection("database")
	if err != nil {
		log.Fatalf("Fail to get section 'database': %v", err)
	}

	db, err = gorm.Open(mysql.New(mysql.Config{
		DSN: fmt.Sprintf("%s:%s@tcp(%s)/%s?charset=utf8mb4&parseTime=True&loc=Local",
			sec.Key("user").String(),
			sec.Key("password").String(),
			sec.Key("host").String(),
			sec.Key("name"),
		),
	}), &gorm.Config{
		NamingStrategy: schema.NamingStrategy{
			SingularTable: true, // odd table name(without "s" behind model's name)
		},
		PrepareStmt: true, // cache the statements
		Logger: logger.New(
			log.New(os.Stdout, "\r\n", log.LstdFlags), // io writer
			logger.Config{
				LogLevel: logger.Error, // Log level
			},
		),
	})

	if err != nil {
		log.Fatalf("Unable to create database: %v", err)
	}

	err = db.AutoMigrate(&Tag{})
	if err != nil {
		log.Println(err)
	}

	sqlDB, err := db.DB()
	if err != nil {
		log.Println(err)
	}
	sqlDB.SetMaxIdleConns(10)
	sqlDB.SetMaxOpenConns(100)

	if err != nil {
		log.Println(err)
	}
}
