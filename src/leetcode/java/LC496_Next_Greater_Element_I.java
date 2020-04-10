package leetcode.java;

// LC496. Next Greater Element I
// https://leetcode.com/problems/next-greater-element-i/

import java.util.HashMap;
import java.util.Stack;

// using stack
// O(M + N)
public class LC496_Next_Greater_Element_I {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack< Integer > stack = new Stack < > ();
        HashMap< Integer, Integer > map = new HashMap < > ();
        int[] res = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[i] > stack.peek())
                map.put(stack.pop(), nums[i]);
            stack.push(nums[i]);
        }
        while (!stack.empty())
            map.put(stack.pop(), -1);
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }
}
