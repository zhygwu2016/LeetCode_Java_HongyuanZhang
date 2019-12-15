package Online_Assessment;

// https://leetcode.com/discuss/interview-question/411357/

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Zombie_in_Matrix {
    public static void main(String[] args) {
        int[][] grid = {
                { 0, 1, 1, 0, 1 },
                { 0, 1, 0, 1, 0 },
                { 0, 0, 0, 0, 1 },
                { 0, 1, 0, 0, 0 }
        };
        System.out.println(minDays(grid));
    }

    private static int minDays(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int target = grid.length * grid[0].length;
        int cnt = 0, res = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j] == 1) {
                    q.offer(new int[] {i,j});
                    cnt++;
                }
            }
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!q.isEmpty()) {
            int size = q.size();
            if(cnt == target)
                return res;
            for(int i=0;i<size;i++) {
                int[] cur = q.poll();
                for(int[] dir : dirs) {
                    int ni = cur[0] + dir[0];
                    int nj = cur[1] + dir[1];
                    if(ni >=0 && ni < grid.length && nj >=0 && nj < grid[0].length && grid[ni][nj] == 0) {
                        cnt++;
                        q.offer(new int[] {ni, nj});
                        grid[ni][nj] = 1;
                    }
                }
            }
            res++;
        }
        return -1;
    }
}



class Solution {
    private static final int ZOMBIE = 1;
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minHours(List<List<Integer>> grid) {
        int people = 0;
        Queue<Point> zombies = new ArrayDeque<>();
        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.get(0).size(); c++) {
                if (grid.get(r).get(c) == ZOMBIE) {
                    zombies.add(new Point(r, c));
                } else {
                    people++;
                }
            }
        }

        if (people == 0) return 0;

        for (int hours = 1; !zombies.isEmpty(); hours++) {
            for (int sz = zombies.size(); sz > 0; sz--) {
                Point zombie = zombies.poll();

                for (int[] dir : DIRS) {
                    int r = zombie.r + dir[0];
                    int c = zombie.c + dir[1];

                    if (isHuman(grid, r, c)) {
                        people--;
                        if (people == 0) return hours;
                        grid.get(r).set(c, ZOMBIE);
                        zombies.add(new Point(r, c));
                    }
                }
            }
        }
        return -1;
    }

    private boolean isHuman(List<List<Integer>> grid, int r, int c) {
        return r >= 0 && r < grid.size() &&c >= 0 && c < grid.get(0).size() && grid.get(r).get(c) != ZOMBIE;
    }

    private static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}


// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
class Solution_OA
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumHours(int rows, int columns, List<List<Integer> > grid)
    {
        // WRITE YOUR CODE HERE
        Queue<Point> q = new LinkedList<>();
        int target = grid.size() * grid.get(0).size();
        int cnt = 0, res = 0;

        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.get(0).size(); c++) {
                if (grid.get(r).get(c) == 1) {
                    q.offer(new Point(r, c));
                    cnt++;
                }
            }
        }

        int[][] dirs = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        while (!q.isEmpty()) {
            int size = q.size();
            if (cnt == target) {
                return res;
            }
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                for (int[] dir : dirs) {
                    int r = cur.r + dir[0];
                    int c = cur.c + dir[1];

                    if (r >= 0 && r < grid.size() && c >= 0 && c < grid.get(0).size()
                            && grid.get(r).get(c) == 0) {
                        cnt++;
                        q.offer(new Point(r, c));
                        grid.get(r).set(c, 1);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private static class Point {
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    // METHOD SIGNATURE ENDS
}