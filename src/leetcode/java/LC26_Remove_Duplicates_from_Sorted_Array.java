package leetcode.java;

// 26. Remove Duplicates from Sorted Array
// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

public class LC26_Remove_Duplicates_from_Sorted_Array {
    public int removeDuplicates(int[] nums) {
        if(nums == null) return -1;
        if(nums.length <= 1) return nums.length;

        int slow = 1, fast = 1;

        for(fast = 1; fast < nums.length; fast++){
            if(nums[slow - 1] != nums[fast]){
                nums[slow++] = nums[fast];
            }
        }

        return slow;
    }
}
