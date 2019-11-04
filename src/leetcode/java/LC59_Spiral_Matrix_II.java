package leetcode.java;

// 59. Spiral Matrix II
// https://leetcode.com/problems/spiral-matrix-ii/

public class LC59_Spiral_Matrix_II {
    public int[][] generateMatrix(int n) {
        if(n <= 0) return null;

        final int[][] result = new int[n][n];

        int rowStart = 0, colStart = 0;
        int rowEnd = n - 1, colEnd = n - 1;
        int temp = 1;

        while(rowStart <= rowEnd && colStart <= colEnd){
            // Traverse right
            for(int i = colStart; i <= colEnd; i++){
                result[rowStart][i] = temp++;
            }
            rowStart++;

            // Traverse down
            for(int i = rowStart; i <= rowEnd; i++){
                result[i][colEnd] = temp++;
            }
            colEnd--;

            // Traverse left
            if(rowStart <= rowEnd){
                for(int i = colEnd; i >= colStart; i--){
                    result[rowEnd][i] = temp++;
                }
                rowEnd--;
            }

            // Traverse up
            if(colStart <= colEnd){
                for(int i = rowEnd; i >= rowStart; i--){
                    result[i][colStart] = temp++;
                }
                colStart++;
            }
        }
        return result;
    }
}
