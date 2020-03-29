package leetcode.java;

// LC694. Number of Distinct Islands
// https://leetcode.com/problems/number-of-distinct-islands/

import java.util.HashSet;

public class LC694_Number_of_Distinct_Islands {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int row = grid.length, col = grid[0].length;
        HashSet<String> set = new HashSet<>();
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, visited, 's');
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, boolean[][] visited, char dir) {
        int row = grid.length, col = grid[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col
                || visited[i][j] || grid[i][j] == 0) {
            return;
        }

        visited[i][j] = true;
        sb.append(dir);

        dfs(grid, i, j + 1, sb, visited, 'r'); // right
        dfs(grid, i + 1, j, sb, visited, 'd'); // down
        dfs(grid, i, j - 1, sb, visited, 'l'); // left
        dfs(grid, i - 1, j, sb, visited, 'u'); // up
        sb.append('b'); // 记录T型拐点的位置
    }
}
