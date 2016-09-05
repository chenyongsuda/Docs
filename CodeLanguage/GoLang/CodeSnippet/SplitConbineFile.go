package main

import (
	"fmt"
	"io/ioutil"
	"math"
	"os"
	"strconv"
)

const chunkSize int64 = 1 << 17

//
func main() {
	fileInfo, err := os.Stat("eros.7z")
	if err != nil {
		fmt.Println(err)
	}

	num := int(math.Ceil(float64(fileInfo.Size()) / float64(chunkSize)))
	fmt.Println(num)
	fi, err := os.OpenFile("eros.7z", os.O_RDONLY, os.ModePerm)
	if err != nil {
		fmt.Println(err)
		return
	}
	b := make([]byte, chunkSize)
	var i int64 = 1
	for ; i <= int64(num); i++ {

		fi.Seek((i-1)*(chunkSize), 0)

		if len(b) > int((fileInfo.Size() - (i-1)*chunkSize)) {
			b = make([]byte, fileInfo.Size()-(i-1)*chunkSize)
		}

		fi.Read(b)

		f, err := os.OpenFile("./"+strconv.Itoa(int(i))+".db", os.O_CREATE|os.O_WRONLY, os.ModePerm)
		if err != nil {
			fmt.Println(err)
			return
		}
		f.Write(b)
		f.Close()
	}
	fi.Close()

	fii, err := os.OpenFile("test.zip", os.O_CREATE|os.O_WRONLY|os.O_APPEND, os.ModePerm)
	if err != nil {
		fmt.Println(err)
		return
	}
	for i := 1; i <= num; i++ {
		f, err := os.OpenFile("./"+strconv.Itoa(int(i))+".db", os.O_RDONLY, os.ModePerm)
		if err != nil {
			fmt.Println(err)
			return
		}
		b, err := ioutil.ReadAll(f)
		if err != nil {
			fmt.Println(err)
			return
		}
		fii.Write(b)
		f.Close()
	}
}
