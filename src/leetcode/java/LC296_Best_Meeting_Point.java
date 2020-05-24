package leetcode.java;

// LC296. Best Meeting Point
// https://leetcode.com/problems/best-meeting-point/

import java.util.ArrayList;
import java.util.List;

// 算法加强
// 数学方法：对于每个为1的坐标，x坐标放在一起取中位数，y坐标放在一起取中位数，即best point
// 与LC317区别：LC317. Shortest Distance from All Buildings 里有障碍，
// 有障碍之后就不能用数学方法直接算出来，只能用其他非数学方法（BFS）
public class LC296_Best_Meeting_Point {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null) return 0;

        int row = grid.length, col = grid[0].length;

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        // 为什么traverse两遍：让xList与yList分别都是sorted
        // 取中位数直接get(size/2)即可
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    xList.add(i);
                }
            }
        }
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (grid[i][j] == 1) {
                    yList.add(j);
                }
            }
        }

        if (xList.size() == 0) return -1;

        return medianDis(xList) + medianDis(yList);
    }

    private int medianDis(List<Integer> list) {
        int len = list.size();
        int median = list.get(len / 2);

        int sum = 0;
        for (int i : list) {
            sum += Math.abs(i - median);
        }

        return sum;
    }
}
