package leetcode.java;

// LC698. Partition to K Equal Sum Subsets
// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solution/

import java.util.Arrays;

public class LC698_Partition_to_K_Equal_Sum_Subsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)  return false;

        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        int target = sum / k;

        Arrays.sort(nums);

        int row = nums.length - 1;
        if (nums[row] > target)  return false;

        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }

        return search(new int[k], row, nums, target);
    }

    private boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0)  return true;

        int v = nums[row--];

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target))  return true;
                groups[i] -= v;
            }
            if (groups[i] == 0)  break;
        }

        return false;
    }
}
/*
Time Complexity: O(k^{N-k} k!), where N is the length of nums, and k is as given.
As we skip additional zeroes in groups, naively we will make O(k!) calls to search,
then an additional O(k^{N-k}) calls after every element of groups is nonzero.

It is because of if (groups[i] == 0) break; optimization.
Without this we should have to make K calls from each call, so complexity will be O(K^N).
With this break condition, we are allowed to use only the first group for the first number we check.
When we reach second number, we can use group No.1 and group No.2.
Then, when we process the K-th number, all K groups are allowed to use.
So, overall complexity becomes 1·2·3·…·K·K·K·…·K and it is O(K!·K^(N-K)).
 */
