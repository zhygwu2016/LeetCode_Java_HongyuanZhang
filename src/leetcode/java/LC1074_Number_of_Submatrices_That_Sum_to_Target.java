package leetcode.java;

// 1074. Number of Submatrices That Sum to Target
// https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/discuss/303750/JavaC%2B%2BPython-Find-the-Subarray-with-Target-Sum

public class LC1074_Number_of_Submatrices_That_Sum_to_Target {
    public int numSubmatrixSumTarget(int[][] A, int target) {
        int res = 0, m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                A[i][j] += A[i][j - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Map<Integer, Integer> counter = new HashMap<>();
                counter.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) {
                    cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0);
                    res += counter.getOrDefault(cur - target, 0);
                    counter.put(cur, counter.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
