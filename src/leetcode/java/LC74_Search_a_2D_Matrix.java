package leetcode.java;


// 74. Search a 2D Matrix
// https://leetcode.com/problems/search-a-2d-matrix/

// 大弓
public class LC74_Search_a_2D_Matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }

        int row = matrix.length, col = matrix[0].length;

        int left = 0, right = row * col - 1;

        // use the third binary search here to avoid post processing
        while(left <= right){
            int mid = left + (right - left)/2;

            int rowIndex = mid / col;
            int colIndex = mid % col;

            int temp = matrix[rowIndex][colIndex];

            if(temp == target) return true;
            if(temp < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }
}

