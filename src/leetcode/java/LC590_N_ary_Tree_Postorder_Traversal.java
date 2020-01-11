package leetcode.java;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LC590_N_ary_Tree_Postorder_Traversal {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if(root == null) return result;
        postOrder(result, root);
        return result;
    }

    private void postOrder(LinkedList<Integer> result, Node root){
        Node cur = root;
        Stack<Node> stack = new Stack<>();
        stack.push(cur);

        while(!stack.isEmpty()){
            cur = stack.pop();
            result.addFirst(cur.val);
            for(Node n : cur.children){
                stack.push(n);
            }
        }
    }

}

