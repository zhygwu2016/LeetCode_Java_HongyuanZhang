package leetcode.java;

// 285. Inorder Successor in BST
// https://leetcode.com/problems/inorder-successor-in-bst/

public class LC285_Inorder_Successor_in_BST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) return null;

        TreeNode pre = null, cur = root;

        while(cur != null){
            if(cur.val > p.val){
                pre = cur;
                cur = cur.left;
            }else{
                cur = cur.right;
            }
        }
        return pre;
    }
}

