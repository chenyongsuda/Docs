package main

import (
	"fmt"
	"os"
)

//
func main() {
	loopDir("../../", "kiteq-v0.0.8", 1)
}

func loopDir(baseDir string, dirName string, count int) {
	dir, _ := os.Open(baseDir + dirName)
	list, _ := dir.Readdir(100)
	for _, f := range list {
		tpcount := count
		if f.IsDir() {
			for i := 0; i < tpcount; i++ {
				fmt.Print("		")
			}
			fmt.Println(f.Name())
			tpcount++
			loopDir(baseDir+dirName+"/", f.Name(), tpcount)
		} else {
			for i := 0; i < tpcount; i++ {
				fmt.Print("		")
			}
			fmt.Println(f.Name())
		}
	}
}
