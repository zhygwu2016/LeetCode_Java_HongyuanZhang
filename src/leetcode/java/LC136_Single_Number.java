package leetcode.java;

// 136. Single Number
// https://leetcode.com/problems/single-number/

public class LC136_Single_Number {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            result = result ^ nums[i];
        }
        return result;
    }
}
