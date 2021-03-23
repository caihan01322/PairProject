package models

import (
	"backend/conf"
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
	ID        uint `gorm:"primarykey" json:"-"`
	CreatedAt time.Time
	UpdatedAt time.Time
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
				LogLevel: logger.Info, // Log level
			},
		),
	})
	if err != nil {
		log.Fatalf("Unable to create database: %v", err)
	}

	err = db.SetupJoinTable(&User{}, "Papers", &UserFav{})
	if err != nil {
		log.Println(err)
	}
	err = db.AutoMigrate(&User{}, &Paper{}, &Word{}, &WordPoint{})
	if err != nil {
		log.Println(err)
	}

	sqlDB, err := db.DB()
	if err != nil {
		log.Println(err)
	}
	sqlDB.SetMaxIdleConns(sec.Key("max_idle").MustInt(10))
	sqlDB.SetMaxOpenConns(sec.Key("max_open").MustInt(100))

	if err != nil {
		log.Println(err)
	}

}
