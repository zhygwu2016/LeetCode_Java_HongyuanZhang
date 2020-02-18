package leetcode.java;

// LC1120. Maximum Average Subtree
// https://leetcode.com/problems/maximum-average-subtree/

public class LC1120_Maximum_Average_Subtree {
    public double maximumAverageSubtree(TreeNode root) {
        return helper(root)[2];
    }

    private double[] helper(TreeNode root) {
        if (root == null) {
            return new double[] {0, 0, 0}; // sum, count & average of nodes
        }
        double[] left = helper(root.left), right = helper(root.right); // recurse to children
        double largerChild = Math.max(left[2], right[2]); // larger of the children
        double sum = root.val + left[0] + right[0];
        double count = 1 + left[1] + right[1]; // sum & count of subtree rooted at n
        double maxAverage = Math.max(largerChild, sum / count);
        return new double[] {sum, count, maxAverage};
    }
}
