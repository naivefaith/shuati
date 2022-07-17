package main

import (
	"fmt"
)

// 312.戳气球（标准dp解法，放缩一边）
func maxCoins(nums []int) int {
	boundSliceLen := len(nums) + 2
	boundCoin := make([]int, boundSliceLen)
	boundCoin[0] = 1
	boundCoin[boundSliceLen-1] = 1
	for i := 1; i < boundSliceLen-1; i++ {
		boundCoin[i] = nums[i-1]
	}
	dpArray := make([][]int, 0)
	for i := 0; i < boundSliceLen; i++ {
		dpArray = append(dpArray, make([]int, boundSliceLen))
	}
	for i := 1; i < boundSliceLen; i++ { //从一边开始搜索，左右都行，延长一边
		for j := i - 1; j >= 0; j-- { //从延长的前沿开始往回搜索，新加入的元素是否改变了动态规划保存的最优解
			for k := i - 1; k > j; k-- { //对每个元素进行搜索最优解，是否优于当前最优
				tempMax := dpArray[i][k] + dpArray[k][j] + boundCoin[i]*boundCoin[k]*boundCoin[j]
				if tempMax > dpArray[i][j] {
					dpArray[i][j] = tempMax
				}
			}
		}
	}
	return dpArray[boundSliceLen-1][0]
}

// 312.戳气球（递归记忆化搜索）
func maxCoinsV2(nums []int) (int, *[][]int) {
	boundSliceLen := len(nums) + 2
	boundCoin := make([]int, boundSliceLen)
	boundCoin[0] = 1
	boundCoin[boundSliceLen-1] = 1
	for i := 1; i < boundSliceLen-1; i++ {
		boundCoin[i] = nums[i-1]
	}
	dpArray := make([][]int, 0)
	for i := 0; i < boundSliceLen; i++ {
		dpArray = append(dpArray, make([]int, boundSliceLen))
	}
	return DFS(0, boundSliceLen-1, &boundCoin, &dpArray), &dpArray
}

func DFS(left int, right int, boundCoinPt *[]int, resultMatrixPt *[][]int) int {
	if left >= right-1 {
		return 0
	}
	boundCoin := *boundCoinPt
	resultMatrix := *resultMatrixPt
	if resultMatrix[left][right] > 0 {
		return resultMatrix[left][right]
	}
	for i := left + 1; i < right; i++ {
		sum := boundCoin[left] * boundCoin[i] * boundCoin[right]
		sum += DFS(left, i, boundCoinPt, resultMatrixPt) + DFS(i, right, boundCoinPt, resultMatrixPt)
		if sum > resultMatrix[left][right] {
			resultMatrix[left][right] = sum
		}
	}
	return resultMatrix[left][right]
}

func main() {
	nums := []int{3, 1, 5, 8}
	//back := maxCoins(nums)
	back, matrixPt := maxCoinsV2(nums)
	fmt.Println(matrixPt)
	fmt.Println(back)
}
