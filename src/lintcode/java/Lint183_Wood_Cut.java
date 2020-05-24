package lintcode.java;

// LintCode183. Wood Cut
// https://www.lintcode.com/problem/wood-cut/description

/*
Given n pieces of wood with length L[i] (integer array).
Cut them into small pieces to guarantee you could have equal or more than k pieces
with the same length.
What is the longest length you can get from the n pieces of wood?
Given L & k, return the maximum length of the small pieces.

You couldn't cut wood into float length.
If you couldn't get >= k pieces, return 0.

Example 1
Input:
L = [232, 124, 456]
k = 7
Output: 114
Explanation: We can cut it into 7 pieces if any piece is 114cm long,
however we can't cut it into 7 pieces if any piece is 115cm long.

Example 2
Input:
L = [1, 2, 3]
k = 7
Output: 0
Explanation: It is obvious we can't make it.
 */

public class Lint183_Wood_Cut {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        int max = 0;
        int maxLen = 0;

        for (int l : L) {
            maxLen = Math.max(maxLen, l);
        }
        int start = 1, end = maxLen;

        // long sum = 0;
        // for (int l : L) {
        //     maxLen = Math.max(maxLen, l);
        //     sum += (long)l;
        // }
        // int start = 1, end = Math.min(maxLen, (int)sum / k);
        // 优化： end = min(maxLen, average(sum, k));
        // 可能越界

        while (start <= end) {
            int len = start + (end - start) / 2;
            int curVal = check(L, len);
            if (curVal >= k) {
                start = len + 1;
                max = Math.max(max, len);
            } else {
                end = len - 1;
            }
        }

        return max;
    }

    private int check(int[] L, int len) {
        int sum = 0;
        for (int l : L) {
            sum += l / len;
        }
        return sum;
    }
}
