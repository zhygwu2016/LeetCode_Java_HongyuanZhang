package leetcode.java;

// 54. Spiral Matrix
// https://leetcode.com/problems/spiral-matrix/

import java.util.ArrayList;
import java.util.List;

public class LC54_Spiral_Matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        final List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return result;
        }

        int rowStart = 0, colStart = 0;
        int rowEnd = matrix.length - 1, colEnd = matrix[0].length - 1;

        while(rowStart <= rowEnd && colStart <= colEnd){
            // Traverse right
            for(int i = colStart; i <= colEnd; i++){
                result.add(matrix[rowStart][i]);
            }
            rowStart++;

            // Traverse down
            for(int i = rowStart; i <= rowEnd; i++){
                result.add(matrix[i][colEnd]);
            }
            colEnd--;

            // Traverse left
            if(rowStart <= rowEnd){
                for(int i = colEnd; i >= colStart; i--){
                    result.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            // Traverse up
            if(colStart <= colEnd){
                for(int i = rowEnd; i >= rowStart; i--){
                    result.add(matrix[i][colStart]);
                }
                colStart++;
            }

        }

        return result;
    }
}

/*
Input     [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output    [1,2,3,4,8,12,11,10,9,5,6,7,6]
Expected  [1,2,3,4,8,12,11,10,9,5,6,7]
 */
