package leetcode.java;

// LC543. Diameter of Binary Tree
// https://leetcode.com/problems/diameter-of-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LC543_Diameter_of_Binary_Tree {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)  return 0;
        int res = 1;
        helper(root, res);
        return res - 1;
    }

    private int helper(TreeNode root, int res) {
        if (root == null) return 0;

        int left = helper(root.left, res);
        int right = helper(root.right, res);
        res = Math.max(res, left + right + 1);

        return Math.max(left, right) + 1;
    }
}
