package leetcode.java;

// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/

import javax.swing.tree.TreeNode;

public class LC226_Invert_Binary_Tree {
}

/*
public class LC226_Invert_Binary_Tree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return root;
        }

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
}

 */

//public class LC226_Invert_Binary_Tree {
//    public TreeNode invertTree(TreeNode root) {
//        if(root == null || (root.left == null && root.right == null)){
//            return root;
//        }
//
//        TreeNode temp = root.left;
//        root.left = root.right;
//        root.right = temp;
//
//        invertTree(root.left);
//        invertTree(root.right);
//
//        return root;
//    }
//}
