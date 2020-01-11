package leetcode.java;

// LC994. Rotting Oranges
// https://leetcode.com/problems/rotting-oranges/

import java.util.LinkedList;
import java.util.Queue;

public class LC994_Rotting_Oranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length, col = grid[0].length;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    int code = i * col + j;
                    queue.offer(code);
                }
            }
        }

        int ans = 0;
        int[][] dirs = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                int rr = cur / col, cc = cur % col;
                for (int[] dir : dirs) {
                    int ii = rr + dir[0];
                    int jj = cc + dir[1];
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && grid[ii][jj] == 1) {
                        grid[ii][jj] = 2;
                        flag = true;
                        int ncode = ii * col + jj;
                        queue.offer(ncode);
                    }
                }
            }
            // 不设立flag, 最后腐烂的橙子也在queue中，多记一次数。
            // [[2,1,1],[1,1,0],[0,1,1]] 不设立flag 结果为5（应为4）
            if (flag == true) ans++;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return ans;
    }
}
