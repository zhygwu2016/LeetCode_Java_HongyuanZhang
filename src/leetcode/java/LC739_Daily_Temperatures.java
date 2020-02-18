package leetcode.java;

// LC739. Daily Temperatures
// https://leetcode.com/problems/daily-temperatures/

import java.util.Stack;

public class LC739_Daily_Temperatures {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i= 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int t = stack.pop();
                ans[t] = i - t;
            }
            stack.push(i);
        }
        return ans;
    }
}
