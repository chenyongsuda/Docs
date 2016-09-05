package main

import (
	"fmt"
	"math"
	"os"
	"strconv"
)

const chunkSize = 1 << 17

func main() {
	//
	src := "eros.7z"
	//1. get file size / trunck size
	bigFile, _ := os.Stat(src)
	fmt.Println("FileSize:", bigFile.Size(), "SplitSize:", chunkSize, "num:", math.Ceil(float64(bigFile.Size())/float64(chunkSize)))
	//2. cope bit to new files
	num := int64(math.Ceil(float64(bigFile.Size()) / float64(chunkSize)))
	var i int64 = 0
	for ; i < num; i++ {
		dest := strconv.Itoa(int(i)) + ".db"
		if num-1 == i {
			//fmt.Println("start2:", strconv.Itoa(int(chunkSize*i)), "len:", strconv.Itoa(int(chunkSize*num-bigFile.Size())))
			Wirte2NewFile(src, dest, chunkSize*num, bigFile.Size()-chunkSize*(num-1))
		} else {
			//fmt.Println("start:", strconv.Itoa(int(chunkSize*i)), "len:", strconv.Itoa(chunkSize))
			Wirte2NewFile(src, dest, chunkSize*num, chunkSize)
		}
	}
}

func Wirte2NewFile(src string, dest string, offset int64, blen int64) {
	srcFile, _ := os.OpenFile(src, os.O_RDONLY, os.ModePerm)
	srcFile.Seek(offset, os.SEEK_SET)
	bytes := make([]byte, blen)
	srcFile.Read(bytes)
	dstFile, _ := os.OpenFile(dest, os.O_CREATE|os.O_WRONLY|os.O_APPEND, os.ModePerm)
	dstFile.Write(bytes)
	dstFile.Close()
	srcFile.Close()
}
