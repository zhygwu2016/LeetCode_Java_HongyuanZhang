package leetcode.java;

// 35. Search Insert Position
// https://leetcode.com/problems/search-insert-position/

public class LC35_Search_Insert_Position {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(target < nums[0]) return 0;
        if(target > nums[nums.length - 1]) return nums.length;
        int left = 0, right = nums.length-1, mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

// 大弓
class LC35 {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length-1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (target < nums[mid]){
                right = mid;
            } else {
                left = mid;
            }
        }

        // if we use first binary search method,the post processing is a little complicated
        if(nums[left] > target) return left > 0 ? left - 1 : 0;
        else if (nums[left] == target) return left;
        else if (nums[left] < target && target <= nums[right]) return right;
        else return right + 1;
    }
}

