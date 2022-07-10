package main

import (
	"fmt"
	"sort"
)

type IntArray []int
type IntArrays []IntArray

func (array IntArrays) Len() int {
	return len(array)
}

func (array IntArrays) Less(i, j int) bool {
	if array[i][0] < array[j][0] {
		return true
	} else {
		return false
	}
}

func (array IntArrays) Swap(i, j int) {
	array[i], array[j] = array[j], array[i]
}

//1.基于排序重写时间复杂度O(nlogn)，与l无关
func isCovered_sort(ranges [][]int, left int, right int) bool {
	my2DArraay := IntArrays{}
	for _, intArray := range ranges {
		my2DArraay = append(my2DArraay, IntArray(intArray))
	}
	sort.Sort(my2DArraay)
	for _, unitRange := range my2DArraay {
		leftValue := unitRange[0]
		rightValue := unitRange[1]
		if leftValue <= left && left <= rightValue {
			left = rightValue + 1
		}
	}
	return left > right
}

//2.基于差分数组实现时间复杂度O(n+l)
func isCovered_diffAray(ranges [][]int, left int, right int) bool {
	//取消数组长度限制
	maxRange := right
	for _, v := range ranges {
		if v[1] > maxRange {
			maxRange = v[1]
		}
	}
	length := maxRange + 2
	diff := make([]int, length, 2*length)
	for _, v := range ranges {
		diff[v[0]]++
		diff[v[1]+1]--
	}
	count := 0
	for i := 1; i < len(diff); i++ {
		count += diff[i]
		if count <= 0 && left <= i && i <= right {
			return false
		}
	}
	return true
}

func main() {
	array := [][]int{
		{1, 1},
	}
	//fmt.Println(isCovered_sort(array, 1, 2))
	fmt.Println(isCovered_diffAray(array, 50, 50))
}
