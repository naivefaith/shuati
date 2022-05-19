import java.util.Map;
import java.util.TreeMap;

public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length < 1) {
            return null;
        }
        int[] result = new int[intervals.length];
        TreeMap<Integer, Integer> bRTree = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] temp = intervals[i];
            bRTree.put(temp[0], temp[0]);
        }
        for (int i = 0; i < intervals.length; i++) {
            int[] temp = intervals[i];
            Map.Entry<Integer, Integer> tempEntry = bRTree.ceilingEntry(temp[1]);
            if (tempEntry != null) {
                result[i] = tempEntry.getValue();
            } else {
                result[i] = -1;
            }
        }
        return result;
    }
}

