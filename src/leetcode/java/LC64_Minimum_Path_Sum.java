package leetcode.java;

// 64. Minimum Path Sum
// https://leetcode.com/problems/minimum-path-sum/

public class LC64_Minimum_Path_Sum {
    public int minPathSum(int[][] grid) {
        final int row = grid.length;
        final int col = grid[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(i == 0 && j == 0){
                    continue;
                } else if(i == 0 && j != 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if(i != 0 && j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if(i != 0 && j != 0){
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }

        return grid[row - 1][col - 1];
    }
}
