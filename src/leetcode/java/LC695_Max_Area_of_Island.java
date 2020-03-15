package leetcode.java;

// LC695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/

import java.util.LinkedList;
import java.util.Queue;

// DFS
public class LC695_Max_Area_of_Island {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int currentArea = dfs(grid, i, j, visited);
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int row = grid.length, col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col
                || visited[i][j] || grid[i][j] == 0) {
            return 0;
        }

        visited[i][j] = true;

        int currentArea = 1;

        currentArea += dfs(grid, i - 1, j, visited);
        currentArea += dfs(grid, i + 1, j, visited);
        currentArea += dfs(grid, i, j - 1, visited);
        currentArea += dfs(grid, i, j + 1, visited);

        return currentArea;
    }
}

// BFS
class LC695_BFS {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, //上
            {1, 0}, // 下
            {0, -1}, // 左
            {0, 1} // 右
    };

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        boolean[][] visited = new boolean[nr][nc];
        int maxArea = 0;

        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    int currentArea = 1;
                    visited[r][c] = true;
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.poll();
                        int row = id / nc;
                        int col = id % nc;

                        for (int[] direction : DIRECTIONS) {
                            int ii = row + direction[0];
                            int jj = col + direction[1];
                            if (ii >= 0 && ii < nr && jj >= 0 && jj < nc
                                    && grid[ii][jj] == 1 && !visited[ii][jj]) {
                                neighbors.add(ii * nc + jj);
                                currentArea++;
                                visited[ii][jj] = true;
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }

        return maxArea;
    }
}
