package leetcode.java;

// 36. Valid Sudoku
// https://leetcode.com/problems/valid-sudoku/

import java.util.HashSet;
import java.util.Set;

public class LC36_Valid_Sudoku {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return false;
        }

        int row = board.length, col = board[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(!isParticallyValid(board, i, i, 0, 8)) return false;
                if(!isParticallyValid(board, 0, 8, j, j)) return false;
            }
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!isParticallyValid(board, i*3, i*3 + 2, j*3, j*3 + 2)) return false;
            }
        }

        return true;
    }

    private boolean isParticallyValid(char[][] board, int x1, int x2, int y1, int y2){
        Set<Character> set = new HashSet<>();

        for(int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                char temp = board[i][j];

                if(temp != '.' && !set.add(temp)) return false;
            }
        }

        return true;
    }
}
