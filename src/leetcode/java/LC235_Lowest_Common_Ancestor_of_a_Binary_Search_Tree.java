package leetcode.java;

// 235. Lowest Common Ancestor of a Binary Search Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

import javax.swing.tree.TreeNode;

public class LC235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
}

/*
public class LC235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        if(root == p || root == q) return root;
        if(p == q) return p;
        if(p.val > q.val) return lowestCommonAncestor(root, q, p);

        if(root.val > p.val && root.val < q.val) return root;
        else if(root.val < p.val) return lowestCommonAncestor(root.right, p, q);
        else return lowestCommonAncestor(root.left, p, q);
    }
}

 */
