package leetcode.java;

// 783. Minimum Distance Between BST Nodes
// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

public class LC783_Minimum_Distance_Between_BST_Nodes {
    public int minDiffInBST(TreeNode root) {
        Integer[] result = new Integer[2];
        result[1] = Integer.MAX_VALUE;
        inOrderTraverse(root,result);
        return result[1];
    }

    private void inOrderTraverse(TreeNode root, Integer[] result){
        if(root == null) return;

        inOrderTraverse(root.left, result);

        if(result[0] != null){
            result[1] = Math.min(result[1], root.val - result[0]);
        }

        result[0] = root.val;

        inOrderTraverse(root.right, result);
    }
}

/*
In a binary search tree, an in-order traversal outputs the values of
the tree in order. By remembering the previous value in this order,
we could iterate over each possible difference, keeping the smallest one.
 */
//class Solution {
//    Integer prev, ans;
//    public int minDiffInBST(TreeNode root) {
//        prev = null;
//        ans = Integer.MAX_VALUE;
//        dfs(root);
//        return ans;
//    }
//
//    public void dfs(TreeNode node) {
//        if (node == null) return;
//        dfs(node.left);
//        if (prev != null)
//            ans = Math.min(ans, node.val - prev);
//        prev = node.val;
//        dfs(node.right);
//    }
//}
