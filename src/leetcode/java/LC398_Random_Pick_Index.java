package leetcode.java;

// LC398. Random Pick Index
// https://leetcode.com/problems/random-pick-index/

import java.util.Arrays;
import java.util.Random;

// 类似Reservoir Sampling水库采样
public class LC398_Random_Pick_Index {

    private int[] nums;

    public LC398_Random_Pick_Index(int[] nums) {
        if (nums == null) return;

        this.nums = Arrays.copyOf(nums, nums.length);
    }

    public int pick(int target) {
        int len = nums.length, ret = -1, count = 0;

        for (int i = 0; i < len; i++) {
            if (target == nums[i]) {
                count++;
                int rand = new Random().nextInt(count);
                if (rand == 0) {
                    ret = i;
                }
            }
        }

        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
