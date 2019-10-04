package leetcode.java;

// 173. Binary Search Tree Iterator
// https://leetcode.com/problems/binary-search-tree-iterator/

import javax.swing.tree.TreeNode;
import java.util.Stack;

public class LC173_Binary_Search_Tree_Iterator {
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack();
        pushLeftNode(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode temp = stack.pop();
        if(temp.right != null){
            pushLeftNode(temp.right);
        }
        return temp.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushLeftNode(TreeNode root){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
