package leetcode.java;

// 62. Unique Paths
// https://leetcode.com/problems/unique-paths/

public class LC62_Unique_Paths {
    public int uniquePaths(int m, int n) {
        final int[][] result = new int[m][n];

        for(int i = 0; i < m; i++){
            result[i][0] = 1;
        }
        for(int j = 1; j < n; j++){
            result[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }

        return result[m - 1][n - 1];
    }
}