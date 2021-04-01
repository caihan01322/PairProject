package cache

import (
	"backend/models"
	"github.com/go-redis/redis"
	"log"
)

var (
	RDB = redis.NewClient(&redis.Options{
		Addr:     "127.0.0.1:6379",
		Password: "",
		DB:       0,
	})
)

// search and cache the single query's results's ids
func GetSearch(q []string) []string {
	keys := make([]string, len(q))
	for i := range q {
		key := "search|" + q[i]

		exist, err := RDB.Exists(key).Result()
		if err != nil {
			log.Println(err)
			return []string{}
		}

		if exist == 0 {
			// query cache not exist
			ids := models.GetPaperIDsBySearch(q[i])
			if len(ids) == 0 {
				continue
			}
			set := make([]interface{}, len(ids))
			for j := range ids {
				set[j] = ids[j]
			}
			if add := RDB.SAdd(key, set...).Err(); add != nil {
				log.Println(add)
			}
		}

		keys[i] = key
	}
	union := RDB.SUnion(keys...)
	result, err := union.Result()
	if err != nil {
		log.Println(err)
	}
	log.Println(len(result))
	return result
}
