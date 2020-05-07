package leetcode.java;

// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/

import java.util.LinkedList;
import java.util.Queue;
// 算法加强 dfs
class LC200_DFS {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, visited);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        int row = grid.length, col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col
                || visited[i][j] || grid[i][j] == '0') {
            return;
        }

        visited[i][j] = true;

        dfs(grid, i - 1, j, visited);
        dfs(grid, i + 1, j, visited);
        dfs(grid, i, j - 1, visited);
        dfs(grid, i, j + 1, visited);
    }
}

// 每个点 被摸了5次
// O(mn)
// follow up 最大岛的面积

// bfs
class
LC200_BFS {

    private static final int[][] DIRECTIONS = {
            {-1, 0}, //上
            {1, 0}, // 下
            {0, -1}, // 左
            {0, 1} // 右
    };

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.poll();
                        int row = id / nc;
                        int col = id % nc;

                        for (int[] direction : DIRECTIONS) {
                            int ii = row + direction[0];
                            int jj = col + direction[1];
                            if (ii >= 0 && ii < nr && jj >= 0 && jj < nc && grid[ii][jj] == '1') {
                                neighbors.add(ii * nc + jj);
                                grid[ii][jj] = '0';
                            }
                        }

                        // if (row - 1 >= 0 && grid[row-1][col] == '1') {
                        //   neighbors.add((row-1) * nc + col);
                        //   grid[row-1][col] = '0';
                        // }
                        // if (row + 1 < nr && grid[row+1][col] == '1') {
                        //   neighbors.add((row+1) * nc + col);
                        //   grid[row+1][col] = '0';
                        // }
                        // if (col - 1 >= 0 && grid[row][col-1] == '1') {
                        //   neighbors.add(row * nc + col-1);
                        //   grid[row][col-1] = '0';
                        // }
                        // if (col + 1 < nc && grid[row][col+1] == '1') {
                        //   neighbors.add(row * nc + col+1);
                        //   grid[row][col+1] = '0';
                        // }
                    }
                }
            }
        }

        return num_islands;
    }
}


// dfs
public class LC200_Number_of_Islands {
    private void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }


    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}


// Union Find
// O(K * log(M*N)), K is #1 in matrix
class LC200_UnionFind {

    private class UnionFind {
        public int size, row, col; // size: number of islands
        public int[] ids, sz;

        public UnionFind(int row, int col) {
            this.size = 0;
            this.row = row;
            this.col = col;
            int len = row * col;
            ids = new int[len];
            sz = new int[len];
            for (int i = 0; i < len; i++) {
                ids[i] = -1;
                sz[i] = 1;
            }
        }

        private int root(int p) {
            int tempP = p;
            while (ids[p] != p) {
                ids[p] = ids[ids[p]];
                p = ids[p];
            }
            ids[tempP] = p;
            return p;
        }

        public boolean find(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (sz[rootP] < sz[rootQ]) {
                union(q, p);
            } else {
                ids[rootQ] = rootP;
                sz[rootP] += sz[rootQ];
                this.size--;
            }
        }

        public int index(int i, int j) {
            return i * col + j;
        }

        public void addIsland(int p) {
            if (ids[p] == -1) {
                ids[p] = p;
                size++;
            }
        }

        public boolean isIsland(int p) {
            return ids[p] != -1;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;
        UnionFind uf = new UnionFind(row, col);

        int[][] directions = {
                {-1, 0}, //上
                {1, 0}, // 下
                {0, -1}, // 左
                {0, 1} // 右
        };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    int p = uf.index(i, j);
                    uf.addIsland(p);

                    for (int[] dir : directions) {
                        int rowIdx = i + dir[0], colIdx = j + dir[1];

                        int q = uf.index(rowIdx, colIdx);

                        if (rowIdx >= 0 && rowIdx < row && colIdx >= 0 && colIdx < col
                                && uf.isIsland(q) && !uf.find(p, q)) {
                            uf.union(p, q);
                        }
                    }
                }
            }
        }

        return uf.size;
    }
}

