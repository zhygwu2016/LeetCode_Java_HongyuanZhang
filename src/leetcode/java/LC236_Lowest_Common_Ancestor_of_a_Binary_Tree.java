package leetcode.java;

// 236. Lowest Common Ancestor of a Binary Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

import javax.swing.tree.TreeNode;

public class LC236_Lowest_Common_Ancestor_of_a_Binary_Tree {
}

/*
public class LC236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        if(p == root || q == root) return root;
        if(p == q) return p;

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if(leftNode != null && rightNode != null) return root;
        return leftNode != null ? leftNode : rightNode;
    }
}

 */
