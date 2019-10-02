package leetcode.java;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
                                4
                      2                 6
                 1         3        5        7

                 pre:  4 2 1 3 6 5 7
                 in:   1 2 3 4 5 6 7
                 post: 1 3 2 5 7 6 4
 */

public class LC145_Binary_Tree_Postorder_Traversal {
}

/*
public class LC145_Binary_Tree_Postorder_Traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();

        if(root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            result.addFirst(cur.val);

            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }

        return result;
    }
}

 */
