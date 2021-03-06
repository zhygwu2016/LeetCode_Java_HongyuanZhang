package leetcode.java;

// 220. Contains Duplicate III
// https://leetcode.com/problems/contains-duplicate-iii/

import java.util.TreeSet;

public class LC220_Contains_Duplicate_III {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k <= 0 || t < 0){
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();

        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(Long.valueOf(nums[i - k - 1]));
            Long upper = (long)nums[i] + t;
            Long lower = (long)nums[i] - t;

            // The lower(E ele) method of TreeSet class in Java is used to
            // return the greatest element in this set which is strictly
            // less than the given element.
            // If no such element exists in this TreeSet collection then
            // this method returns a NULL.
            Long temp = set.lower(upper + 1);

            if(temp != null && temp >= lower){
                return true;
            }

            set.add(Long.valueOf(nums[i]));
        }

        return false;
    }
}

// Time: O(N logk)
