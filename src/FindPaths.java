import java.util.*;

//576
public class FindPaths {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] matrix = new int[m][n];
        int[][] dpMatrix = new int[m][n];
        final int MOD = (int) 1e9 + 7;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Boolean outBound = (m < 2 && n < 2) || m < 0 || n < 0;
        int outBoundCount = 0;
        if (outBound) {
            return 4;
        }
        matrix[startRow][startColumn] = 1;
        for (int nowMove = 0; nowMove < maxMove; nowMove++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] > 0) {
                        int count = 0;
                        for (int k = 0; k < move.length; k++) {
                            int i1 = i + move[k][0];
                            int j1 = j + move[k][1];
                            if (i1 > m - 1 || i1 < 0 || j1 > n - 1 || j1 < 0) {
                                count++;
                            } else {
                                dpMatrix[i1][j1] = (matrix[i][j] + dpMatrix[i1][j1]) % MOD;
                            }
                        }
                        for (int k = 0; k < count; k++) {
                            outBoundCount = (matrix[i][j] + outBoundCount) % MOD;
                        }
                    }
                    matrix[i][j] = 0;
                }
            }
            int[][] tempMatrix = matrix;
            matrix = dpMatrix;
            dpMatrix = tempMatrix;
        }
        return outBoundCount;
    }
}
