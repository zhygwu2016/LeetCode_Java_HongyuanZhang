package leetcode.java;

// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/

import javax.swing.tree.TreeNode;

public class LC110_Balanced_Binary_Tree {
}

/*
public class LC110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        if(Math.abs(leftDepth - rightDepth) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode root){
        if(root == null) return 0;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}

 */
