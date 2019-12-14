package leetcode.java;

// 255. Verify Preorder Sequence in Binary Search Tree
// https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/

import java.util.Stack;

public class LC255_Verify_Preorder_Sequence_in_Binary_Search_Tree {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        for (int n : preorder) {
            if(n < low) return false;

            // stack from bottom to top: decreasing order
            while(!stack.isEmpty() && n > stack.peek()){
                low = stack.pop();
            }
            stack.push(n);
        }

        return true;
    }
}
