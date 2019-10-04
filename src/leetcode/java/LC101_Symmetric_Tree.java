package leetcode.java;

// 101. Symmetric Tree
// https://leetcode.com/problems/symmetric-tree/

import javax.swing.tree.TreeNode;

public class LC101_Symmetric_Tree {

}

/*
public class LC101_Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return true;

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode r1, TreeNode r2){
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null) return false;

        if(r1.val != r2.val) return false;

        return isSymmetric(r1.left, r2.right) && isSymmetric(r1.right,r2.left);
    }
}

 */
