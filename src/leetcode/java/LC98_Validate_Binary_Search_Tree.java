package leetcode.java;

// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/

import java.util.LinkedList;

public class LC98_Validate_Binary_Search_Tree {
    public boolean isValidBST(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return true;
        }

        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, long lower, long upper){
        if(root == null) return true;

        if(root.val <= lower || root.val >= upper) return false;

        return isValid(root.left, lower, root.val)
                && isValid(root.right, root.val, upper);
    }
}

// Approach 2: Iteration
class LC98_2 {
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }
}

class solutionLC98{
    public boolean isValidBST(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return true;
        }

        TreeNode[] pre = new TreeNode[1];
        boolean[] result = new boolean[]{true};

        inOrder(pre, root, result);

        return result[0];
    }

    private void inOrder(TreeNode[] pre, TreeNode root,boolean[] result){
        if(root == null) return;

        inOrder(pre, root.left, result);

        if(pre[0] == null){
            pre[0] = root;
        }else{
            if(pre[0].val >= root.val) result[0] = false;
            else{
                pre[0] = root;
            }
        }

        inOrder(pre, root.right, result);
    }
}

