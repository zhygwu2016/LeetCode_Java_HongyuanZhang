package leetcode.java;

// 80. Remove Duplicates from Sorted Array II
// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

public class LC80_Remove_Duplicates_from_Sorted_Array_II {
    public int removeDuplicates(int[] nums) {
        if(nums == null) return -1;
        if(nums.length <= 2) return nums.length;

        // This can be implemented to n (n >= 1)
        int slow = 2, fast = 2;

        for(fast = 2; fast < nums.length; fast++){
            // slow - n
            if(nums[slow - 2] != nums[fast]){
                nums[slow++] = nums[fast];
            }
        }

        return slow;
    }
}
