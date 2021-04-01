package json

import (
	"fmt"
	"io/ioutil"
	"os"
)

type Paper struct {
	Title         string    `json:"title"`
	Articlenumber string    `json:"articlenumber"`
	Doilink       string    `json:"doilink"`
	Keywords      []Keyword `json:"keywords"`
}

type Keyword struct {
	Type string   `json:"type"`
	KWD  []string `json:"kwd"`
}

var dir = "C:\\Users\\xpy91\\Desktop\\json\\data\\CVPR"

func mergeFilename(info os.FileInfo) string {
	return dir + "\\" + info.Name()
}

func main() {
	rd, _ := ioutil.ReadDir(dir)
	for _, info := range rd {
		fmt.Println(mergeFilename(info))
	}
}
