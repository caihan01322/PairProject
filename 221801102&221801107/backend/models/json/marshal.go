package json

import (
	"fmt"
	"time"
)

type JTime time.Time

func (t *JTime) MarshalJSON() ([]byte, error) {
	stamp := fmt.Sprintf("\"%s\"", time.Time(*t).Format("2006 01"))
	return []byte(stamp), nil
}
