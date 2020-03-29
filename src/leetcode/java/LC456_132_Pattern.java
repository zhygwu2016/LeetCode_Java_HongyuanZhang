package leetcode.java;

// LC456. 132 Pattern
// https://leetcode.com/problems/132-pattern/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Brute Force
// nums[i], 以及[0, i]之间的最小值min
// 则nums[i] >= min
// 从i+1往后找，如果能找到之后的一个元素，在min和nums[i]之间，我们就找到了132
// Time: O(n^2)  Space: O(1)
public class LC456_132_Pattern {
    public boolean find132pattern(int[] nums) {
        int min_i = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length - 1; j++) {
            min_i = Math.min(min_i, nums[j]);
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < nums[j] && min_i < nums[k])
                    return true;
            }
        }
        return false;
    }
}

// 找到 nums[i - 1] >= nums[i] 这个拐点
// [start, i - 1]成为一个interval
// 如果之后的元素，能有一个在interval范围内，即是有132
// Time: O(n^2)  Space: O(n)
class LC456_Intervals {
    public boolean find132pattern(int[] nums) {
        List< int[] > intervals = new ArrayList< >();
        int i = 1, s = 0;
        while (i < nums.length) {
            if (nums[i] <= nums[i - 1]) {
                if (s < i - 1)
                    intervals.add(new int[] {nums[s], nums[i - 1]});
                s = i;
            }
            for (int[] a: intervals)
                if (nums[i] > a[0] && nums[i] < a[1])
                    return true;
            i++;
        }
        return false;
    }
}

// 记录array min[]，每个值为当前的最小值
// nums: [6, 12, 3, 4, 6, 11, 20]
// min:  [6,  6, 3, 3, 3,  3,  3]
// 从后往前遍历，如果nums[j] > min[j], 我们在stack里找是否有符合条件的元素
// 往前遍历时把nums[] 元素往stack里放
// Time: O(n)  Space: O(n)
class LC456_Stack {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)  return false;

        Stack<Integer> stack = new Stack<>();

        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }

        return false;
    }
}

// 方法同上，未使用stack
class LC456_Using_Array_As_Stack {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)  return false;

        Stack<Integer> stack = new Stack<>();

        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while(k < nums.length && nums[k] <= min[j]) {
                    k++;
                }
                if (k < nums.length && nums[k] < nums[j]) {
                    return true;
                }
                nums[--k] = nums[j];
            }
        }

        return false;
    }
}
