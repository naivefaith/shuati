import java.util.*;

public class ThreeSumClosest {
    public int threeSumClosestSolution(int[] nums, int target) {
        Arrays.sort(nums);
        int selectIndex = 0;
        int firIndex = 1;
        int secIndex = nums.length;
        int tempCloseNum = 0;
        int moveFlag = 0;
        if (nums.length < 3) {
            return Integer.MIN_VALUE;
        } else {
            tempCloseNum = nums[0] + nums[1] + nums[2];
        }
        if (tempCloseNum > target) {
            return tempCloseNum;
        }
        for (selectIndex = 0; selectIndex < nums.length - 2; selectIndex++) {
            firIndex = selectIndex + 1;
            secIndex = nums.length - 1;
            while (firIndex < secIndex) {
                Integer temp = nums[selectIndex] + nums[firIndex] + nums[secIndex];
                if (temp.equals(target)) {
                    return temp;
                } else if (Math.abs(target - tempCloseNum) > Math.abs(target - temp)) {
                    tempCloseNum = temp;
                }
                if (temp < target) {
                    firIndex++;
                    moveFlag = 0;
                } else {
                    if (moveFlag == 0) {
                        secIndex--;
                        moveFlag = 1;
                    } else if (firIndex > selectIndex + 1) {
                        firIndex--;
                    }else {
                        secIndex--;
                    }
                }
            }
        }
        return tempCloseNum;
    }

//    public static void main(String[] args) {
//        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
//        int target = 82;
//        ThreeSumClosest ob = new ThreeSumClosest();
//        System.out.println(ob.threeSumClosestSolution(nums, target));
//    }
}
