package leetcode.java;

// LC99. Recover Binary Search Tree
// https://leetcode.com/problems/recover-binary-search-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LC99_Recover_Binary_Search_Tree {
    public void recoverTree(TreeNode root) {
        TreeNode[] mistake = new TreeNode[2];
        mistake[0] = null;
        mistake[1] = null;

        check(root, mistake);

        if (mistake[0] != null && mistake[1] != null) {
            int temp = mistake[0].val;
            mistake[0].val = mistake[1].val;
            mistake[1].val = temp;
        }
    }

    private TreeNode prev = null;

    private void check(TreeNode root, TreeNode[] mistake) {
        if (root == null)  return;

        check(root.left, mistake);

        if (prev != null && prev.val > root.val) {
            // keep the FIRST and LAST violate node
            mistake[1] = root;
            if (mistake[0] == null) {
                mistake[0] = prev;
            }
            // 必须加if (mistake[0] == null) 判断
            // 举例：input [1,3,null,null,2]
        }

        prev = root;

        check(root.right, mistake);
    }
}
