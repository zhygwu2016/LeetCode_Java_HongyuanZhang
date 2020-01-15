package lintcode.java;

// LintCode 632. Binary Tree Maximum Node
// https://www.lintcode.com/problem/binary-tree-maximum-node/description

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
public class Lint632_Binary_Tree_Maximum_Node {
    /*
     * @param root: the root of tree
     * @return: the max node
     */
    public TreeNode maxNode = null;
    public int maxValue = Integer.MIN_VALUE;

    public TreeNode maxNode(TreeNode root) {
        // write your code here
        if (root == null) return root;

        int maxRes = helper(root);
        return maxNode;
    }

    public int helper(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;

        int left = helper(root.left);
        int right = helper(root.right);

        if (root.val > maxValue) {
            maxValue = root.val;
            maxNode = root;
        }

        return maxValue;
    }
}
