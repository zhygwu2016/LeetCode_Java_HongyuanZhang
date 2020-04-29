package leetcode.java;

// LC450. Delete Node in a BST
// https://leetcode.com/problems/delete-node-in-a-bst/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// 被删的是key
// key是leaf node：直接删
// key有right child: 找到其successor，key值换成successor值。对于successor，recursively作此操作
// key有left child: 找到其predecessor，key值换成predecessor值。对于predecessor，recursively作此操作
public class LC450_Delete_Node_in_a_BST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else { // key == root.val 找到了要删除的node
            // 是leaf node，直接删
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                // 有right child: 找到其successor，key值换成successor值。
                // 对于successor，recursively作此操作
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                // 有left child: 找到其predecessor，key值换成predecessor值。
                // 对于predecessor，recursively作此操作
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    private int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    private int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }
}
