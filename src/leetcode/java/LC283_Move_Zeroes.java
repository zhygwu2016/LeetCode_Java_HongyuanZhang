package leetcode.java;

// 283. Move Zeroes
// https://leetcode.com/problems/move-zeroes/

public class LC283_Move_Zeroes {
    public void moveZeroes(int[] nums){
        if(nums == null || nums.length <= 1) return;

        int zero = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                swap(nums, zero++, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
