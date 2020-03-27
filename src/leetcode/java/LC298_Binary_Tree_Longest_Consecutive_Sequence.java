package leetcode.java;

// LC298. Binary Tree Longest Consecutive Sequence
// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LC298_Binary_Tree_Longest_Consecutive_Sequence {
    private int max = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        helper(root);
        return max;
    }

    // int helper(root): 以root为start的Longest Consecutive Sequence长度
    //                注：不是以root为根的Longest Consecutive Sequence长度
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lLen = helper(root.left);
        int rLen = helper(root.right);

        int localLen = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            localLen = lLen + 1;
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            localLen = Math.max(localLen, rLen + 1);
        }

        max = Math.max(max, localLen);
        return localLen;
    }
}
