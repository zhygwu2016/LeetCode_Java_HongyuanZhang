package leetcode.java;

// 240. Search a 2D Matrix II (VS 74)
// https://leetcode.com/problems/search-a-2d-matrix-ii/

public class LC240_Search_a_2D_Matrix_II_VS74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0){
            return false;
        }

        int row = matrix.length, col = matrix[0].length;

        int rowIndex = row - 1, colIndex = 0;

        // this is not a binary search,but uses some principles from binary search
        while(rowIndex >= 0 && colIndex < col){
            int num = matrix[rowIndex][colIndex];

            if(num == target) return true;
            else if(target < num){
                rowIndex--;
            }else{
                colIndex++;
            }
        }
        return false;
    }
}

