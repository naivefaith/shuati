import java.util.HashMap;

public class NumberOfUniqueGoodSubsequences {
//    public int numberOfUniqueGoodSubsequences(String binary) {
//        Integer count = 1;
//        int[] array = {0, 0};
//        int has0 = 0;
//        final int MOD = (int) 1e9 + 7;
//        for (int i = binary.length() - 1; i >= 0; i--) {
//            int index = binary.charAt(i) - '0';
//            if (index == 0) {
//                has0 = 1;
//            }
//            array[index] = (array[0] + array[1] + 1) % MOD;
//        }
//        return array[1] + has0;
//    }

    public int numberOfUniqueGoodSubsequences(String binary) {
        int[] array = {0, 0};
        Integer count = 1;
        int head0 = 0;
        int has0 = 0;
        int i = 0;
        final int MOD = (int) 1e9 + 7;
        try {
            while (binary.charAt(i) == '0') {
                head0 = 1;
                i++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return count;
        }
        for (; i < binary.length(); i++) {
            int temp = binary.charAt(i) - '0';
            Integer beforeAdd = count;
            count += count; //count里的必定是1开头的
            if (temp == 0) {
                has0 = 1;
                count--;    //需要减去""+0这种情况
                beforeAdd--;
            }
            count = count % MOD;    //小心溢出
            count = count - array[temp];
            array[temp] = beforeAdd;
            count = count % MOD;
        }
        if (has0 == 1 || head0 == 1) {
            count++;
        }
        count--;
        if (count < 0) {
            count = count + MOD;
        }
        return count;
    }
//需要减掉所有以0开头的序列

    public static void main(String[] args) {
        NumberOfUniqueGoodSubsequences s = new NumberOfUniqueGoodSubsequences();
        s.numberOfUniqueGoodSubsequences("101");
    }
}

