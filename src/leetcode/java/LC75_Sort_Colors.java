package leetcode.java;

public class LC75_Sort_Colors {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) return;

        int zero = 0, two = nums.length - 1;

        for(int i = 0; i <= two; i++){
            if(nums[i] == 0) swap(nums, zero++, i);
            else if(nums[i] == 2) swap(nums, two--, i--); //注意这个i--!!!
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

