package leetcode.java;

// LC230. Kth Smallest Element in a BST
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

import java.util.ArrayList;
import java.util.Stack;

// Approach 1: Recursion
public class LC230_Kth_Smallest_Element_in_a_BST {
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }
}

// 2. iteration
class LC230_2 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}

// Recursion O(1) space
class LC230_3 {
    public int kthSmallest(TreeNode root, int k) {
        // traverse in order, decrement k, return val when it gets to 0
        // should check for null here, but the task description allows us not to
        return kthElt(root, new int[]{k});
    }
    private Integer kthElt(TreeNode node, int[] k){
        if (node==null) return null;
        Integer retVal= kthElt(node.left, k);
        if (retVal!=null) return retVal;
        if (--k[0]==0) return node.val;
        return kthElt(node.right, k);
    }
}
