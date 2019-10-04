package leetcode.java;

import javax.swing.tree.TreeNode;

// 100. Same Tree
// https://leetcode.com/problems/same-tree/

public class LC100_Same_Tree {
}

/*
public class LC100_Same_Tree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

 */
