package leetcode.java;

// 37. Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/

public class LC37_Sudoku_Solver {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return;
        }
        solveBoard(board);
    }

    private boolean solveBoard(char[][] board){
        final int row = board.length;
        final int col = board[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){
                        if(isValid(board, i, j, c)){
                            board[i][j] = c;
                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++){
            final char rowChar = board[i][col];
            final char colChar = board[row][i];
            final char blockChar = board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3];
            if(rowChar != '.' && rowChar == c) return false; // Check Duplicate in rowIndex
            if(colChar != '.' && colChar == c) return false; // Check Duplicate in colIndex
            if(blockChar != '.' && blockChar == c) return false; // Check Duplicate in 3 * 3 blocks
        }

        return true;
    }
}
