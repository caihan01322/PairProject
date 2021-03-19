package main

import (
	"221801102/conf"
	_ "221801102/models" // initialize database
	"fmt"
)

func main() {
	fmt.Println("hello, " + conf.RunMode) // test ini
}
