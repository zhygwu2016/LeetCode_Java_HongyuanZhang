package leetcode.java;

// 52. N-Queens II
// https://leetcode.com/problems/n-queens-ii/

public class LC52_N_Queens_II {
    public int totalNQueens(int n) {
        if(n <= 1) return n;

        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        int[] count = new int[1];
        dfs(board, count, n, 0);
        return count[0];
    }

    private void dfs(char[][] board, int[] count, int n, int colIndex){
        if(colIndex == n){
            count[0]++;
            return;
        }

        for(int i = 0; i < n; i++){
            if(isValid(board, i, colIndex)){
                board[i][colIndex] = 'Q';
                dfs(board, count, n, colIndex + 1);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int x, int y){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < y; j++){
                if(board[i][j] == 'Q' && (x == i || Math.abs(x - i) == Math.abs(y - j))){
                    return false;
                }
            }
        }
        return true;
    }
}
