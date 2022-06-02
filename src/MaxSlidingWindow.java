import java.util.*;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> indexDeque = new ArrayDeque<>();
        int i = 0;
        for (; i < k - 1; i++) {
            while (!indexDeque.isEmpty() && nums[i] >= nums[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }
        int[] result = new int[nums.length - k + 1];
        for (; i < nums.length; i++) {
            while (!indexDeque.isEmpty() && nums[i] >= nums[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            result[i - k + 1] = nums[indexDeque.getFirst()];
            while (indexDeque.getFirst() <= i - k + 1) {
                indexDeque.removeFirst();
                if(indexDeque.isEmpty()){
                    break;
                }
            }
        }
        return result;
    }

//    public static void main(String[] args) {
//        MaxSlidingWindow s = new MaxSlidingWindow();
//        int[] array = {1,3,1,2,0,5};
//        array = s.maxSlidingWindow(array, 3);
//        for (Integer i : array) {
//            System.out.println(i);
//        }
//    }
}