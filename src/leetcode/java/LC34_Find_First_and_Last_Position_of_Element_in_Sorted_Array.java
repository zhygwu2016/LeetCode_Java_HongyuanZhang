package leetcode.java;

// 34. Find First and Last Position of Element in Sorted Array
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

public class LC34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        if(nums == null || nums.length == 0) return targetRange;

        // 先找左边第一个target
        int left = 0, right = nums.length - 1, mid = 0;
        while(left + 1 < right){
            mid = left + (right - left)/2;
            if(nums[mid] < target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if(nums[left] == target) {
            targetRange[0] = left;
        }else if(nums[right] == target) {
            targetRange[0] = right;
        }
        // nums = {2,2}; target = 2; Expected: [0,1]

        //如果左边找不到，右边肯定找不到
        if(targetRange[0] == -1) return targetRange;

        left = 0; right = nums.length - 1; mid = 0;
        while(left + 1 < right){
            mid = left + (right - left)/2;
            if(nums[mid] <= target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if(nums[left] == target) targetRange[1] = left;
        if(nums[right] == target) targetRange[1] = right;

        return targetRange;
    }
}


// 大弓
class LC34 {
    public int[] searchRange (int[] nums, int target){
        if(nums == null || nums.length == 0) return new int[] {-1,-1};

        final int[] result = new int[2];

        result[0]= findLeftIndex(nums, target);
        result[1]= findRightIndex (nums, target);

        return result;
    }

    // To find the left index, we need to move left even we find the target
    private int findLeftIndex (int[] nums, int target){
        int left = 0, right= nums.length - 1;
        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(target <= nums[mid]) {
                right = mid;
            }else{
                left = mid;
            }
        }
        if(nums[left] == target) return left;
        return nums[right] == target ? right : -1;
    }

    // To find the right index, we need to move right even we find the target
    private int findRightIndex (int[] nums, int target){
        int left = 0, right= nums.length - 1;
        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(target >= nums[mid]) {
                left = mid;
            }else{
                right = mid;
            }
        }
        if(nums[right] == target) return right;
        return nums[left] == target ? left : -1;
    }
}
