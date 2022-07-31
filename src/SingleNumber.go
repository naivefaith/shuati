package main

import "fmt"

func singleNumber(nums []int) []int {
	xorSum := 0
	for _, num := range nums {
		xorSum = xorSum ^ num
	}
	lowBit := xorSum & -xorSum //获取xorSum最后一位1的位置
	//以3为例 二进制0011，-3二进制按位取反+1，二进制1101
	//0011 & 1101 = 0001
	//以4为例 二进制0100，-4二进制按位取反+1，二进制1100
	//0100 & 1100 = 0100
	type1, type2 := 0, 0
	for _, num := range nums {
		if num&lowBit > 0 { //如果对于lsb对应位不为1的数作与运算，得到结果为0
			type1 ^= num
		} else {
			type2 ^= num
		}
	}
	return []int{type1, type2}
}

func main() {
	temp := 1 & 2
	fmt.Println(temp)
	//list := make([]int, 0)
	//for i := 1; i < 15; i++ {
	//	list = append(list, i)
	//}
	//for i := 1; i < 15; i++ {
	//	list = append(list, i)
	//}
	//list = append(list, 99)
	//list = append(list, -1)
	//fmt.Println(singleNumber(list))
}
