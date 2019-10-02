package leetcode.java;

// 111. Minimum Depth of Binary Tree
// https://leetcode.com/problems/minimum-depth-of-binary-tree/

import javax.swing.tree.TreeNode;

public class LC111_Minimum_Depth_of_Binary_Tree {
}

/*
public class LC111_Minimum_Depth_of_Binary_Tree {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;

        final int minLeftDepth = minDepth(root.left);
        final int minRightDepth = minDepth(root.right);

        if(root.left != null && root.right != null){
            return Math.min(minLeftDepth, minRightDepth) + 1;
        }else{
            return root.left != null ? minLeftDepth + 1 : minRightDepth + 1;
        }
    }
}

 */
