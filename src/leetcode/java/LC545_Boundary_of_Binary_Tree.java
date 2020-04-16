package leetcode.java;

// LC545. Boundary of Binary Tree
// https://leetcode.com/problems/boundary-of-binary-tree/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 打印左边，打印leaf nodes，再打印右边界。右边界用stack实现自下向上
public class LC545_Boundary_of_Binary_Tree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)  return res;

        if (!isLeaf(root)) res.add(root.val);

        // 打印左边界
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }
        }

        // 打印leaf nodes
        addLeaves(res, root);

        // 自下而上打印右边界
        Stack<Integer> stack = new Stack<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                stack.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }
}


class LC545_preorder {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> left_boundary = new ArrayList<>();
        List<Integer> right_boundary = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();

        preorder(root, left_boundary, right_boundary, leaves, 0);

        left_boundary.addAll(leaves);
        left_boundary.addAll(right_boundary);

        return left_boundary;
    }

    private boolean isLeaf(TreeNode cur) {
        return cur.left == null && cur.right == null;
    }

    private boolean isRightBoundary(int flag) {
        return flag == 2;
    }

    private boolean isLeftBoundary(int flag) {
        return flag == 1;
    }

    private boolean isRoot(int flag) {
        return flag == 0;
    }

    private int leftChildFlag(TreeNode cur, int flag) {
        if (isLeftBoundary(flag) || isRoot(flag)) {
            return 1;
        } else if (isRightBoundary(flag) && cur.right == null) {
            return 2;
        } else {
            return 3;
        }
    }

    private int rightChildFlag(TreeNode cur, int flag) {
        if (isRightBoundary(flag) || isRoot(flag)) {
            return 2;
        } else if (isLeftBoundary(flag) && cur.left == null) {
            return 1;
        } else {
            return 3;
        }
    }

    public void preorder(TreeNode cur, List<Integer> left_boundary,
                         List<Integer> right_boundary, List<Integer> leaves, int flag) {
        if (cur == null)  return;
        if (isRightBoundary(flag)) {
            right_boundary.add(0, cur.val);
        } else if (isLeftBoundary(flag) || isRoot(flag)) {
            left_boundary.add(cur.val);
        } else if (isLeaf(cur)) {
            leaves.add(cur.val);
        }

        preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
        preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
    }
}