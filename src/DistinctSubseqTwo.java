import java.util.HashMap;
import java.util.HashSet;

//940
public class DistinctSubseqTwo {
    public int distinctSubseqTwo(String s) {
        Integer count = 1;
        HashMap<Character, Integer> charMap = new HashMap<>();
        if (s == null) {
            return count--;
        }
        final int MOD = (int) 1e9 + 7;
        for (int i = 0; i < s.length(); i++) {
            Character tempChar = s.charAt(i);
            Integer beforeAdd = count;
            count += count;
            count = count % MOD;    //小心溢出
            if (charMap.containsKey(tempChar)) {
                count = count - charMap.get(tempChar);
            }
            charMap.put(tempChar, beforeAdd);
            count = count % MOD;    //小心溢出
        }
        count--;
        if (count < 0) {
            count = count + MOD;
        }
        return count;
    }
}
