package leetcode.java;

// LC701. Insert into a Binary Search Tree
// https://leetcode.com/problems/insert-into-a-binary-search-tree/

// Approach 1: Recursion
public class LC701_Insert_into_a_Binary_Search_Tree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        // insert into the right subtree
        if (val > root.val) root.right = insertIntoBST(root.right, val);
            // insert into the left subtree
        else root.left = insertIntoBST(root.left, val);
        return root;
    }
}

// Approach 2: Iteration
class LC701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            // insert into the right subtree
            if (val > node.val) {
                // insert right now
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return root;
                }
                else node = node.right;
            }
            // insert into the left subtree
            else {
                // insert right now
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return root;
                }
                else node = node.left;
            }
        }
        return new TreeNode(val);
    }
}
