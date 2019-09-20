package leetcode.java;

import javax.swing.tree.TreeNode;

public class LC700_Search_in_a_Binary_Search_Tree {

}

// 700. Search in a Binary Search Tree
// https://leetcode.com/problems/search-in-a-binary-search-tree/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
public class LC700_Search_in_a_Binary_Search_Tree {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;

        TreeNode result = root;
        while(result != null){
            if(result.val == val) return result;
            else if(result.val < val) result = result.right;
            else result = result.left;
        }
        return result;
    }
}
 */

