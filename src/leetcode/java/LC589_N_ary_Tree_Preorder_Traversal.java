package leetcode.java;

// 589. N-ary Tree Preorder Traversal
// https://leetcode.com/problems/n-ary-tree-preorder-traversal/

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC589_N_ary_Tree_Preorder_Traversal {
}

/*
public class LC589_N_ary_Tree_Preorder_Traversal {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        preOrder(result, root);

        return result;
    }

    private void preOrder(List<Integer> result, Node root){
        Node cur = root;
        Stack<Node> stack = new Stack<>();

        stack.push(cur);

        while(!stack.isEmpty()){
            cur = stack.pop();
            result.add(cur.val);
            int size = cur.children.size();

            for(int i = size - 1; i >= 0; i--){
                stack.push(cur.children.get(i));
            }
        }
    }
}

 */
