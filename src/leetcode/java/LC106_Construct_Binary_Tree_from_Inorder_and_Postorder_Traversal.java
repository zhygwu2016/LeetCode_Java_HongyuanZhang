package leetcode.java;

// LC106. Construct Binary Tree from Inorder and Postorder Traversal
// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LC106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    int post_idx;
    int[] inorder, postorder;
    // map: key: value in int[]inorder; value: index in int[]inorder
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }

        post_idx = postorder.length - 1;

        int idx = 0;
        for (Integer val : inorder) {
            map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    private TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right) {
            return null;
        }

        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list into left and right subtrees
        int index = map.get(root_val);

        post_idx--;

        root.right = helper(index + 1, in_right);
        root.left = helper(in_left, index - 1);

        return root;
    }
}
