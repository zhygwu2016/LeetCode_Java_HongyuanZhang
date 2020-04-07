package leetcode.java;

// LC549. Binary Tree Longest Consecutive Sequence II
// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/

public class LC549_Binary_Tree_Longest_Consecutive_Sequence_II {

    private int max;

    public int longestConsecutive(TreeNode root) {
        if (root == null)  return 0;

        helper(root);

        return max;
    }

    // 返回int[]{以root为start的递增max长度, 以root为start的递减max长度}
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[]{1, 1};

        if (root.left != null) {
            if (root.val + 1 == root.left.val) {
                res[0] = left[0] + 1;
            } else if (root.val - 1 == root.left.val) {
                res[1] = left[1] + 1;
            }
        }

        if (root.right != null) {
            if (root.val + 1 == root.right.val) {
                res[0] = Math.max(res[0], right[0] + 1);
            } else if (root.val - 1 == root.right.val) {
                res[1] = Math.max(res[1], right[1] + 1);
            }
        }

        // res[0] + res[1] - 1 可以包括三种情况：
        // leftChild-Parent path, Parent - rightChild Path, leftChild - Parent - rightChild Path
        max = Math.max(max, res[0] + res[1] - 1);

        return res;
    }
}
