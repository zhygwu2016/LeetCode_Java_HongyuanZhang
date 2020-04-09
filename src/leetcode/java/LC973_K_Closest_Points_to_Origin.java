package leetcode.java;

// LC973. K Closest Points to Origin
// https://leetcode.com/problems/k-closest-points-to-origin/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// max-heap
public class LC973_K_Closest_Points_to_Origin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] p1, int[] p2) {
                return p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1];
            }
        });

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[K][2];
        while (K-- > 0) {
            res[K] = maxHeap.poll();
        }

        return res;
    }
}

// quick selection
class LC973 {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0
                || points[0] == null || points[0].length == 0 || K <= 0) {
            throw new IllegalArgumentException();
        }

        int index = quickSelection(points, 0, points.length - 1, K);
        // index: 第K大的元素所在的index。此时index左边是所有比它小的元素
        return Arrays.copyOfRange(points, 0, index + 1);
    }

    private int quickSelection(int[][] points, int start, int end, int K) {
        if (start > end) return Integer.MAX_VALUE;

        int pivot = dis(points[end]);
        int index = start;

        for (int i = start; i <= end; i++) {
            if (dis(points[i]) < pivot) {
                swap(points, index++, i);
            }
        }

        swap(points, index, end);

        if (index == K - 1) { // 找到了第K大元素的正确index
            return index;
        } else if (index < K - 1) {
            return quickSelection(points, index + 1, end, K);
        } else {
            return quickSelection(points, start, index - 1, K);
        }
    }

    private int dis(int[] array) {
        return array[0] * array[0] + array[1] * array[1];
    }

    private void swap(int[][] points, int x, int y) {
        int[] tmp = new int[2];
        tmp[0] = points[x][0];
        tmp[1] = points[x][1];

        points[x][0] = points[y][0];
        points[x][1] = points[y][1];

        points[y][0] = tmp[0];
        points[y][1] = tmp[1];
    }
}
