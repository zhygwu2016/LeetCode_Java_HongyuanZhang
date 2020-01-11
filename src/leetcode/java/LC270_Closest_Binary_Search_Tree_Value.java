package leetcode.java;

// 270. Closest Binary Search Tree Value
// https://leetcode.com/problems/closest-binary-search-tree-value/

public class LC270_Closest_Binary_Search_Tree_Value {
    public int closestValue(TreeNode root, double target) {
        if(root == null) return Integer.MAX_VALUE;

        TreeNode cur = root;
        int result = root.val;

        while(cur != null){
            if(cur.val == target) return cur.val;

            if(Math.abs(cur.val - target) < Math.abs(result - target)){
                result = cur.val;
            } else if(cur.val > target){
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return result;
    }
}

