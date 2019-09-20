package leetcode.java;

// 154. Find Minimum in Rotated Sorted Array II
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

public class LC154_Find_Minimum_in_Rotated_Sorted_Array_II {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;

        if(nums[left] < nums[right]) return nums[left];

        /*
         * The basic idea is the same as Find Minimum in Rotated Sorted Array I.
         * When we have the duplicate, just do left++ and right--;
         */

        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == nums[left]){
                left++;
            }else if(nums[mid] == nums[right]){
                right--;
            }else if(nums[left] < nums[mid]){
                left = mid;
            }else{
                right = mid;
            }
        }
        return nums[right] < nums[left] ? nums[right] : nums[left];
    }
}
