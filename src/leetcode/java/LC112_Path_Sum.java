package leetcode.java;

// 112. Path Sum
// https://leetcode.com/problems/path-sum/

import javax.swing.tree.TreeNode;

public class LC112_Path_Sum {
}

/*
public class LC112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null){
            return sum - root.val == 0;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

 */
