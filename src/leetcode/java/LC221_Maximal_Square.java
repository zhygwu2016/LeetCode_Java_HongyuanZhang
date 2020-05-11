package leetcode.java;

// 221. Maximal Square
// https://leetcode.com/problems/maximal-square/

public class LC221_Maximal_Square {
    /*

    dp[i][j]: 以(i, j)为右下角的最大Square面积

    To apply DP, we define the state as the maximal size (square = size * size)
    of the square that can be formed till point(i, j), denoted as dp[i][j].

    For the topmost row (i = 0) and the leftmost column (j = 0), we have
    dp[i][j] = matrix[i][j] - '0', meaning that it can at most form a square of
    size 1 when the matrix has a '1' in that cell.

    When i > 0 and j > 0, if matrix[i][j] = '0', then dp[i][j] = 0 since no square
    will be able to contain the '0' at that cell. If matrix[i][j] = '1', we will
    have dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1, which
    means that the square will be limited by its left, upper and upper-left neighbors.
     */

    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int maxArea = 0;

        for(int j = 0; j < col; j++){
            dp[0][j] = matrix[0][j] - 48;
            maxArea = Math.max(maxArea, dp[0][j]);
        }
        for(int i = 0; i < row; i++){
            dp[i][0] = matrix[i][0] - 48;
            maxArea = Math.max(maxArea, dp[i][0]);
        }

        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(matrix[i][j] - 48 == 0){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
            }
        }
        return maxArea;
    }
}
