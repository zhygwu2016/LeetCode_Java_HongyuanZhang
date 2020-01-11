package leetcode.java;

// 114. Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

public class LC114_Flatten_Binary_Tree_to_Linked_List {
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode[] pre = new TreeNode[1];

        flatten(root, pre);
    }

    private void flatten(TreeNode root, TreeNode[] pre){
        if(root == null) return;

        flatten(root.right, pre);
        flatten(root.left, pre);

        root.right = pre[0];
        root.left = null;
        pre[0] = root;
    }
}
