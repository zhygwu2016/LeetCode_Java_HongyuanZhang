package leetcode.java;

// 270. Closest Binary Search Tree Value
// https://leetcode.com/problems/closest-binary-search-tree-value/

import java.util.*;

// Approach 1: Recursive Inorder + Linear search, O(N) time
class LC270 {
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int closestValue(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    }
}

// Approach 2: Iterative Inorder, O(k) time
class LC270_2 {
    public int closestValue(TreeNode root, double target) {
        LinkedList<TreeNode> stack = new LinkedList();
        long pred = Long.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();

            if (pred <= target && target < root.val)
                return Math.abs(pred - target) < Math.abs(root.val - target) ? (int)pred : root.val;

            pred = root.val;
            root = root.right;
        }
        return (int)pred;
    }
}

// Approach 3: Binary Search, O(H) time
public class LC270_Closest_Binary_Search_Tree_Value {
    public int closestValue(TreeNode root, double target) {
        if(root == null) return Integer.MAX_VALUE;

        TreeNode cur = root;
        int result = root.val;

        while(cur != null){
            if(cur.val == target) return cur.val;

            if(Math.abs(cur.val - target) < Math.abs(result - target)){
                result = cur.val;
            } else if(cur.val > target){
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return result;
    }
}

// 算法加强
class Solution_LC270 {
    public int closestValue(TreeNode root, double target) {
        return helper(root, target, root.val);
    }

    private int helper(TreeNode root, double target, int closest) {
        if (root == null)  return closest;

        if (Math.abs((double)root.val - target) < Math.abs((double)closest - target)) {
            closest = root.val;
        }

        if (root.val < target) {
            closest = helper(root.right, target, closest);
        } else if (root.val > target) {
            closest = helper(root.left, target, closest);
        }

        return closest;
    }
}
