package leetcode.java;

// LC42. Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/

// https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
public class LC42_Trapping_Rain_Water {
    public int trap(int[] height) {
        if (height.length < 3)  return 0;

        int leftmax = height[0];
        int rightmax = height[height.length - 1];
        int res = 0;

        int left = 0;
        int right = height.length - 1;

        while (left <= right) {
            leftmax = Math.max(leftmax, height[left]);
            rightmax = Math.max(rightmax, height[right]);

            if (leftmax < rightmax) {
                // leftmax is smaller than rightmax,
                // so the (leftmax-A[left]) water can be stored
                res += leftmax - height[left];
                left++;
            } else {
                res += rightmax - height[right];
                right--;
            }
        }

        return res;
    }
}
