package leetcode.java;

// LC653. Two Sum IV - Input is a BST
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/

import java.util.ArrayList;
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
// 我们可以用DFS或者BFS来遍历树，用HashSet来找Two Sum
// Time O(n) SpaceO(n) 不过这样没有用到BST的特性。

// in-order traverse 得到一个sorted list
// two pointers
public class LC653_Two_Sum_IV_Input_is_a_BST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int left = 0, right = list.size() - 1;
        while(left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null)  return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}

// 两个stack
// 左stack 沿左方向打开右子树
// 右stack 沿右方向打开左子树
class LC653_Two_Stack {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)  return false;

        Stack<TreeNode> ls = new Stack<>();
        Stack<TreeNode> rs = new Stack<>();

        TreeNode cur;
        // initialize left stack
        cur = root;
        while (cur != null) {
            ls.push(cur);
            cur = cur.left;
        }
        // initialize right stack
        cur = root;
        while (cur != null) {
            rs.push(cur);
            cur = cur.right;
        }

        while (!ls.isEmpty() && !rs.isEmpty()) {
            TreeNode left = ls.peek();
            TreeNode right = rs.peek();
            if ((left.val + right.val == k) && (left.val != right.val)) {
                // left.val != right.val -> 两个栈都有root 注意root.val * 2 = target的情况
                return true;
            } else if (left.val + right.val < k) { // left++
                getNextFromLS(ls);
            } else { // right--
                getNextFromRS(rs);
            }
        }

        return false;
    }

    // 沿左方向打开right subtree
    private void getNextFromLS(Stack<TreeNode> ls) {
        TreeNode top = ls.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            ls.push(cur);
            cur = cur.left;
        }
    }

    // 沿右方向打开left subtree
    private void getNextFromRS(Stack<TreeNode> rs) {
        TreeNode top = rs.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            rs.push(cur);
            cur = cur.right;
        }
    }
}
//O(n)
//每个node进一次栈 出一次栈
//栈的大小logn

