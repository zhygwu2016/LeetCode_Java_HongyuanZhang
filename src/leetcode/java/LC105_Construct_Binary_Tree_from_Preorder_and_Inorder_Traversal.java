package leetcode.java;

// LC105. Construct Binary Tree from Preorder and Inorder Traversal
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class LC105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    int pre_idx;
    int[] preorder, inorder;
    // map: key-> value in int[]inorder; value-> index in int[]inorder
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        if (inorder == null || preorder == null || preorder.length != inorder.length) {
            return null;
        }

        pre_idx = 0;

        int idx = 0;
        for (Integer val : inorder) {
            map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    private TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right) return null;

        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list into left and right subtrees
        int index = map.get(root_val);

        pre_idx++;

        root.left = helper(in_left, index - 1);
        root.right = helper(index + 1, in_right);

        return root;
    }
}
