package leetcode.java;

// LC317. Shortest Distance from All Buildings
// https://leetcode.com/problems/shortest-distance-from-all-buildings/

import java.util.LinkedList;
import java.util.Queue;

// BFS: 从每个是1的点出发做BFS, 在cost[][]上累加各个1点到其的最短距离。
// 最后找到距离最小的点。
public class LC317_Shortest_Distance_from_All_Buildings {
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0){
            return -1;
        }
        int row = grid.length, col = grid[0].length;
        int[][] cost = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1){
                    bfs(grid, cost, i, j);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 0 && cost[i][j] != 0){
                    minDistance = Math.min(cost[i][j], minDistance);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private void bfs(int[][] grid, int[][] cost, int i, int j){
        int row = grid.length, col = grid[0].length;
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;

        int distance = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] cur = queue.poll();
                cost[cur[0]][cur[1]] += distance;

                for(int[] direction : directions){
                    int rowIdx = cur[0] + direction[0], colIdx = cur[1] + direction[1];
                    if(rowIdx >= 0 && rowIdx < row && colIdx >= 0 && colIdx < col
                            && grid[rowIdx][colIdx] == 0
                            && visited[rowIdx][colIdx] == false){
                        queue.offer(new int[] {rowIdx, colIdx});
                        visited[rowIdx][colIdx] = true;
                    }
                }
            }
            distance++;
        }

        // 一种bug情况：0被2围住了，到不了1。把grid中这个0点更新为2。
        for(int idxI = 0; idxI < row; idxI++){
            for(int idxJ = 0; idxJ < col; idxJ++){
                if(grid[idxI][idxJ] == 0 && !visited[idxI][idxJ]){
                    grid[idxI][idxJ] = 2;
                }
            }
        }
    }
}
