package leetcode.java;

// 124. Binary Tree Maximum Path Sum
// https://leetcode.com/problems/binary-tree-maximum-path-sum/

public class LC124_Binary_Tree_Maximum_Path_Sum {
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, max);

        return max[0];
    }

    public int maxPathSum(TreeNode root, int[] max){
        if(root == null) return 0;

        int leftSum = maxPathSum(root.left, max);
        int rightSum = maxPathSum(root.right, max);

        // Take max among left path, right path, full path, or root.val

        // The max from left branch and right branch
        int tempMax = Math.max(leftSum + root.val, rightSum + root.val);

        // The max from root.val and tempMax
        int curMax = Math.max(root.val, tempMax);

        // Keep the global max from either cur max or history max
        max[0] = Math.max(max[0], Math.max(curMax, root.val + leftSum + rightSum));

        // We can only return the curMax here since we can't reuse branches
        return curMax;
    }
}

