package main

import (
	"fmt"
	"io/ioutil"
	"math"
	"os"
	"strconv"
)

const chunkSize = 1 << 17

func main() {
	SplitFile("eros.7z")
	MergeFile("eros.7z", "eros1112.7z")
}

func MergeFile(src string, targetFile string) {
	//1. get file size / trunck size
	bigFile, _ := os.Stat(src)
	//fmt.Println("FileSize:", bigFile.Size(), "SplitSize:", chunkSize, "num:", math.Ceil(float64(bigFile.Size())/float64(chunkSize)))
	//2. cope bit to new files
	num := int64(math.Ceil(float64(bigFile.Size()) / float64(chunkSize)))

	//New File
	outfile, _ := os.OpenFile(targetFile, os.O_WRONLY|os.O_APPEND|os.O_CREATE, os.ModePerm)
	var i int64 = 0
	for ; i < num; i++ {
		dest := strconv.Itoa(int(i)) + ".db"
		//ReadFile
		rf, _ := os.OpenFile(dest, os.O_RDONLY, os.ModePerm)
		b, _ := ioutil.ReadAll(rf)
		fmt.Println("Size:", len(b))
		outfile.Write(b)
		rf.Close()
	}
	outfile.Close()
}

func SplitFile(src string) {
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
			Wirte2NewFile(src, dest, chunkSize*i, bigFile.Size()-chunkSize*(num-1))
		} else {
			//fmt.Println("start:", strconv.Itoa(int(chunkSize*i)), "len:", strconv.Itoa(chunkSize))
			Wirte2NewFile(src, dest, chunkSize*i, chunkSize)
		}
	}
}

func Wirte2NewFile(src string, dest string, offset int64, blen int64) {
	srcFile, _ := os.OpenFile(src, os.O_RDONLY, os.ModePerm)
	srcFile.Seek(offset, os.SEEK_SET)
	bytes := make([]byte, blen)
	srcFile.Read(bytes)
	dstFile, _ := os.OpenFile(dest, os.O_CREATE|os.O_WRONLY, os.ModePerm)
	dstFile.Write(bytes)
	dstFile.Close()
	srcFile.Close()
}
