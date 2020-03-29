package leetcode.java;

// LC99. Recover Binary Search Tree
// https://leetcode.com/problems/recover-binary-search-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LC99_Recover_Binary_Search_Tree {
    public void recoverTree(TreeNode root) {
        TreeNode[] mistake = new TreeNode[2];
        mistake[0] = null;
        mistake[1] = null;

        check(root, mistake);

        if (mistake[0] != null && mistake[1] != null) {
            int temp = mistake[0].val;
            mistake[0].val = mistake[1].val;
            mistake[1].val = temp;
        }
    }

    private TreeNode prev = null;

    private void check(TreeNode root, TreeNode[] mistake) {
        if (root == null)  return;

        check(root.left, mistake);

        if (prev != null && prev.val > root.val) {
            // keep the FIRST and LAST violate node
            mistake[1] = root;
            if (mistake[0] == null) {
                mistake[0] = prev;
            }
            // 必须加if (mistake[0] == null) 判断
            // 举例：input [1,3,null,null,2]
        }

        prev = root;

        check(root.right, mistake);
    }
}
/*
e.g. 某个错了的BST 拉直后是 [1, 5, 3, 4, 2, 6, 7]

int[] mistake 找到两个错位的node （5， 2）

第一次出错：..., 5, 3, ...
第二次出错：..., 4, 2, ...
第一次出错：(比如上面的5， 3)  → 我们要的是mistake[0] → 5
第二次出错：(比如上面的4， 2)  → 我们要的是mistake[1] → 2

我们的code的逻辑是：mistake[1] = root;
第一次出错的时候，我们也记录mistake[1]，反正在第二次出错的时候，
我们也会再改mistake[1]的值。
最终mistake[1]是我们想要的。这样第一次第二次的逻辑就可以合并成一个code了。

 */
