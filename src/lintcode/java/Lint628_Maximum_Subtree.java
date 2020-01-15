package lintcode.java;

// LintCode 628. Maximum Subtree
// https://www.lintcode.com/problem/maximum-subtree/description

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Lint628_Maximum_Subtree {
    /*
     * @param root: the root of tree
     * @return: the max node
     */

    public TreeNode maxNode = null;
    public int maxWeight = Integer.MIN_VALUE;

    public TreeNode maxNode(TreeNode root) {
        // write your code here
        if (root == null) return root;
        int root_weight = helper(root);
        return maxNode;
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;

        int leftWeight = helper(root.left);
        int rightWeight = helper(root.right);

        if (root.val + leftWeight + rightWeight > maxWeight) {
            maxWeight = root.val + leftWeight + rightWeight;
            maxNode = root;
        }

        return root.val + leftWeight + rightWeight;
    }
}

