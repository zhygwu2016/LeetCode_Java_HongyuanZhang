package leetcode.java;

import java.util.LinkedList;
import java.util.List;

// 658. Find K Closest Elements
// https://leetcode.com/problems/find-k-closest-elements/

// 大弓
public class LC658_Find_K_Closest_Elements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        final List<Integer> result = new LinkedList<>();

        if(arr == null || arr.length == 0 || k <= 0) return result;

        k = Math.min(k, arr.length);

        int pos = findClosest(arr, x);

        result.add(arr[pos]);

        addRemaining(result, arr, pos, k-1, x);

        return result;
    }

    // find the one that is closest to the target
    private int findClosest(int[] nums, int target){
        int left = 0, right = nums.length - 1;

        while(left + 1 < right){
            int mid = left + (right - left)/2;

            if(nums[mid] == target) return mid;
            else if(target < nums[mid]) right = mid;
            else left = mid;
        }
        return Math.abs(nums[left] - target) <= Math.abs(nums[right] - target) ? left : right;
    }

    // add the rest elements to result list using two pointers
    private void addRemaining(List<Integer> result, int[] nums, int pos, int count, int target){
        if(count == 0) return;
        int left = pos - 1, right = pos + 1;

        while(left >= 0 && right <= nums.length - 1){
            if(count == 0) break;
            if(Math.abs(nums[left] - target) <= Math.abs(nums[right] - target)){
                result.add(0, nums[left--]);
            }else{
                result.add(nums[right++]);
            }
            count--;
        }

        // 例如有可能左边几个一直是closest 但是左边都加完，还没到k。这个时候就要加右边。
        while(count > 0){
            if(left < 0){
                result.add(nums[right++]);
            }else{
                result.add(0, nums[left--]);
            }
            count--;
        }
    }
}
