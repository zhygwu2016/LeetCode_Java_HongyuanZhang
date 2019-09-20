package leetcode.java;

// 153. Find Minimum in Rotated Sorted Array
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

//大弓
public class LC153_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;

        /*
         * 如果抽象看，未被rotate的array的应该左所示，被rotate的array应该如右所示
         *            *		        *
         *          *		      *
         *        *	    --->  *
         *      * 		        1    b    *
         *    *			               *
         *  *			            *   2
         *
         * 因此在原本binary search的基础之上，得加上如下条件
         * 1.如果一开始nums[left] < nums[right],说明这个array并没有被rotate，
             那nums[left] is the smallest number here
         * 2. In binary search, for the initial position, the mid is either on area 1 or area 2.
         *    If the mid is in area 1 --> nums[left] < nums[mid]:
              left move to mid, and mid will shift to right
         *    If the mid is in area 2 --> nums[left] > nums [mid]:
              right move to mid, and mid will shift left.
         * 3. The remaining process will make left and right closer and also move to the "b" point
         * So finally, the smallest value will be located in either nums[left] or nums [right];
         */

        if(nums[left] < nums[right]) return nums[left];

        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(nums[left] < nums[mid]){
                left = mid;
            }else{
                right = mid;
            }
        }
        return nums[right] < nums[left] ? nums[right] : nums[left];
    }
}


// 自己, nums[mid] < nums[left]有点问题。必须先说nums[mid] < nums[left]，然后else，才会通过。
// class Solution {
//     public int findMin(int[] nums) {
//         if(nums == null || nums.length == 0) return -1;
//         if(nums.length == 1) return nums[0];

//         int left = 0, right = nums.length - 1;
//         if(nums[left] < nums[right]){
//            return nums[0];
//         }

//         while(left <= right){
//             int mid = left + (right - left)/2;
//             if(nums[mid] > nums[mid + 1]) {
//                 return nums[mid + 1];
//             }else{
//                 if(nums[mid] < nums[left]){
//                     right = mid - 1;
//                 }else{
//                     left = mid + 1;
//                 }
//             }

//         }
//         return -1;
//     }
// }
