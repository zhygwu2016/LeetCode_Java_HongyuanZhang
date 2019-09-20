package leetcode.java;

// 162. Find Peak Element
// https://leetcode.com/problems/find-peak-element/

public class LC162_Find_Peak_Element {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return 0;

        int left = 0, right = nums.length - 1;

        while(left + 1 < right){
            int mid = left + (right - left)/2;

            // just compare the nums[mid] and nums[mid - 1]
            if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]){
                return mid;
            }else if( mid - 1 >= 0 && nums[mid] > nums[mid - 1]){
                // make sure that mid - 1 is not out of boundary
                left = mid;
            }else{
                right = mid;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }
}
