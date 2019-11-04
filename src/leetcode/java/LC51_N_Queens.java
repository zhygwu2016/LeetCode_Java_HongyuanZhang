package leetcode.java;

// 51. N-Queens
// https://leetcode.com/problems/n-queens/

import java.util.ArrayList;
import java.util.List;

public class LC51_N_Queens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if(n <= 0) return result;

        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        dfs(board, result, n, 0);
        return result;
    }

    private void dfs(char[][] board, List<List<String>> result, int n, int colIndex){
        if(colIndex == n){
            result.add(convertMatrix(board));
            return;
        }

        for(int i = 0; i < n; i++){
            if(isValid(board, i, colIndex)){
                board[i][colIndex] = 'Q';
                dfs(board, result, n, colIndex + 1);
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

    private List<String> convertMatrix(char[][] board){
        List<String> list = new ArrayList<>();

        for(int i = 0; i < board.length; i++){
            list.add(new String(board[i]));
        }

        return list;
    }
}
