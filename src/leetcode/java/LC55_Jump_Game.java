package leetcode.java;

// 55. Jump Game
// https://leetcode.com/problems/jump-game/

public class LC55_Jump_Game {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return true;

        int reachable = nums[0];

        for(int i = 0; i < nums.length; i++){
            if(i > reachable) return false;
            reachable = Math.max(reachable, nums[i] + i);
        }

        return true;
    }
}
