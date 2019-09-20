package leetcode.java;

// 300. Longest Increasing Subsequence
// https://leetcode.com/problems/longest-increasing-subsequence/

// Solution 1 DP O(n^2)
public class LC300_Longest_Increasing_Subsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxAns = 1;

        for(int i = 0; i < dp.length; i++){
            int maxVal = 0;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    maxVal = Math.max(maxVal, dp[j]);
                }
            }
            dp[i] = maxVal + 1;
            maxAns = Math.max(maxAns, dp[i]);
        }
        return maxAns;
    }
}

// Solution 2 DP + Binary Search
//https://leetcode.com/problems/longest-increasing-subsequence/discuss/374551/Java-O(nlogn)-solution-using-DP-and-binary-search
class LC300_Longest_Increasing_Subsequence_2 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // use patience sorting.
        // The array top stores the smallest value of the top of increasing subsequences of length i
        int[] top = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            // find the leftmost idx of top s.t. top[i] > idx
            int idx = binarySearch(top, nums[i], size);
            top[idx] = nums[i];
            if (idx == size) size++;
        }
        return size;
    }

    private int binarySearch(int[] arr, int val, int end) {
        int left = 0, right = end;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= val) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (arr[left] >= val) return left;
        return right;
    }

}

// Solution 3 大弓 DP + Binary Search
// https://leetcode.com/problems/longest-increasing-subsequence/discuss/374551/Java-O(nlogn)-solution-using-DP-and-binary-search
class LC300_Longest_Increasing_Subsequence_3 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] increaSeq = new int[len];
        int index = 0;
        increaSeq[0] = nums[index++];

        /*
         * Here is a DP solution with binary search principle
         * 1. If nums[i] is larger than the dp[index - 1], then dp[index] = nums[i].
         * This means the nums[i] is in the"correct position".
         * 2. If nums[i] is less than dp[index -1], we need to find which position is in dp[]
         * is the right place for nums[i]
         * find it and assign the value. So that inside of dp[], it is always sorted.
         * 3. More details can refer to the first answer in the discussion, it is pretty clear
         */
        for(int i = 1; i < len; i++){
            if(nums[i] > increaSeq[index - 1]){
                increaSeq[index++] = nums[i];
            } else {
                int pos = findPosition(increaSeq, 0, index - 1, nums[i]);
                increaSeq[pos] = nums[i];
            }
        }
        return index;
    }

    private int findPosition(int[] nums, int start, int end, int target) {
        int left = 0, right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
// {0, 8, 4, 12, 2}
