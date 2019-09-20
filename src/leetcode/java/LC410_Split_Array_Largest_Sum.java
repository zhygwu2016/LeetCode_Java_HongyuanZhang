package leetcode.java;

// 410. Split Array Largest Sum
// https://leetcode.com/problems/split-array-largest-sum/

// binary search
// https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation%3A-8ms-Binary-Search-Java
public class LC410_Split_Array_Largest_Sum {
    public int splitArray(int[] nums, int m) {
        if(nums == null || nums.length == 0 || m < 1) return -1;

        int max = 0;
        long sum = 0;

        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }

        if (m == 1) return (int)sum;

        // binary search
        // here, the left becomes the max number in the nums, the right becomes the total sum
        long left = max; long right = sum;

        while (left <= right) {
            long mid = left + (right - left)/2;
            if (valid(mid, nums, m)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int)left;
    }

    // refer to the first answer in the discussion, it is very clear
    public boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;

        for(int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}


