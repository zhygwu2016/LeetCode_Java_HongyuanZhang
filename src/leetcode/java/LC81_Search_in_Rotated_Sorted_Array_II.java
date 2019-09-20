package leetcode.java;

// 81. Search in Rotated Sorted Array II
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

public class LC81_Search_in_Rotated_Sorted_Array_II {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;

        int left = 0, right = nums.length - 1;
        /*
         * The basic idea is the same as Search in Rotated Sorted Array I,
         * the only difference is the duplicated case
         * if we have nums[left] = nums[mid] or nums[right] = nums[mid],
         * just move the left and right correspondingly
         * The rest of the logic is the same as Rotated Sorted Array I.
         * The time complexity here is O(n) for worst case, when all the nums are duplicated
         */

        while(left <= right){
            int mid = left + (right - left)/2;

            if(target == nums[mid]) return true;
            if(target == nums[left]) return true;
            if(target == nums[right]) return true;

            if(nums[left] == nums[mid]){
                left++;
            }else if(nums[right] == nums[mid]){
                right--;
            }else if(nums[left] < nums[mid]){
                if(nums[left] < target && target < nums[mid]){
                    right = mid -1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && target < nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }

        }
        return false;
    }
}
