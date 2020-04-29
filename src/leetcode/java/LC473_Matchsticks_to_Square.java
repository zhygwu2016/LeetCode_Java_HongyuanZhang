package leetcode.java;

// LC473. Matchsticks to Square
// https://leetcode.com/problems/matchsticks-to-square/

import java.util.Arrays;

// 同 LC698. Partition to K Equal Sum Subsets
public class LC473_Matchsticks_to_Square {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;

        int target = sum / 4;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target)  return false;

        int k = 4;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }

        return search(new int[k], row, nums, target);
    }

    private boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0)  return true;

        int v = nums[row--];

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target))  return true;
                groups[i] -= v;
            }

            if (groups[i] == 0) break;
        }

        return false;
    }
}
