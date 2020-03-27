package leetcode.java;

// LC333. Largest BST Subtree
// https://leetcode.com/problems/largest-bst-subtree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LC333_Largest_BST_Subtree {
    private class Result{
        public int min, max, size;

        public Result(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    private int maxSize = 0;

    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return maxSize;
    }

    private Result helper(TreeNode root) {
        if (root == null)  return new Result(0, 0, 0);
        Result left = helper(root.left);
        Result right = helper(root.right);

        if (left == null || right == null)  return null;

        int size = 1;

        if ((left.size == 0 || left.max < root.val)
                && (right.size == 0 || right.min > root.val)) {
            size = left.size + 1 + right.size;
            maxSize = Math.max(maxSize, size);
        } else {
            return null;
        }

        int min = left.size > 0 ? left.min : root.val;
        int max = right.size > 0 ? right.max : root.val;

        return new Result(min, max, size);
    }
}
