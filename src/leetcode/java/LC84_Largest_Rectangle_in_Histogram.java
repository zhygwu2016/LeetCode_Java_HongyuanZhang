package leetcode.java;

// LC84. Largest Rectangle in Histogram
// https://leetcode.com/problems/largest-rectangle-in-histogram/

import java.util.Stack;

public class LC84_Largest_Rectangle_in_Histogram {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length, max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i <= len; i++) {
            int hei = i < len ? heights[i] : 0;
            while (!stack.isEmpty() && heights[stack.peek()] > hei) {
                int top = stack.pop();
                int area = heights[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                max = Math.max(max, area);
            }
            stack.push(i);
        }
        return max;
    }
}
