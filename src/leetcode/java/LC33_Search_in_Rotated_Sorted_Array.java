package leetcode.java;

// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/

// Soluion 1
// 先找出rotate的点 然后再binary search

public class LC33_Search_in_Rotated_Sorted_Array {
    int[] nums;
    int target;

    public int findRotateIndex(int[] nums, int left, int right){
        if(nums[left] < nums[right]){
            return 0;
        }

        while(left <= right){
            int pivot = left + (right - left)/2;
            if(nums[pivot] > nums[pivot + 1]){
                return pivot + 1;
            }else{
                if(nums[pivot] < nums[left]){
                    right = pivot -1 ;
                }else{
                    left = pivot + 1;
                }
            }
        }
        return 0;
    }

    public int binarySearch(int[] nums, int left, int right) {
        while (left <= right) {
            int pivot = left + (right - left)/2;
            if (nums[pivot] == target) return pivot;
            if (target < nums[pivot]){
                right = pivot - 1;
            }else{
                left = pivot + 1;
            }

        }
        return -1;
    }

    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;

        int n = nums.length;

        // corner case
        if (n == 0) return -1;
        if (n == 1){
            return nums[0] == target ? 0 : -1;
        }

        int rotateIndex = findRotateIndex(nums, 0, n - 1);
        if(nums[rotateIndex] == target) return rotateIndex;
        // if the array is not rotated
        if(rotateIndex == 0){
            return binarySearch(nums, 0, n - 1);
        }
        if(target < nums[0]){
            // search the right side
            return binarySearch(nums, rotateIndex, n - 1);
        }
        if(target >= nums[0]){
            return binarySearch(nums, 0, rotateIndex);
        }
        return -1;
    }
}



// Solution 2 大弓
class LC33 {
    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;

        /*
         * 如果抽象看，未被rotate的array应该左所示，被rotate的array应该如右所示
         *            *		          *
         *          *		        *
         *        *	    --->    *
         *      * 		          1              *
         *    *			                 *
         *  *			               *   2
         *
         * 因此在原本binary search的基础之上，得加上如下条件
         * 1. 如果nums [left] < nums [mid],说明有两种可能：
         *	 a. left 和 mid 都在 1 区域里
         *     b. left 和 mid 都在 2 区域里。
         *    But both condition means left and mid are in same area
         *    因此还得分情况讨论：无论在区域1或者2里，只要满足target在nums [left]与nums [mid]中间，
         *    we should move the right, otherwise left should be moved
         *
         * 2. if nums[left] > nums[mid], this means left is on area 1, and mid is on area 2.
         *	 In such a case, if target is between nums[mid] and nums[right], we should move left,
         *	 otherwise right should be moved
         *
         *	 And it is not ease to do the post check if we want to use the first binary method,
         *	 so the thrid method is recommend here to avoid the post check */

        while(left <= right){
            int mid = left + (right - left)/2;

            if(nums[mid] == target) return mid;
            if(nums[left] == target) return left;
            if(nums[right] == target) return right;

            if(nums[left] < nums[mid]){
                if(target > nums[left] && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(target > nums[mid] && target < nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return  -1;
    }

}
