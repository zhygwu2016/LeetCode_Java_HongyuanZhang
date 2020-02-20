package leetcode.java;

// 429. N-ary Tree Level Order Traversal
// https://leetcode.com/problems/n-ary-tree-level-order-traversal/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC429_N_ary_Tree_Level_Order_Traversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}
        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<List<Integer>> levelOrder(Node root) {
        final List<List<Integer>> result = new ArrayList<>();

        if(root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            while(size-- > 0){
                Node Node = queue.poll();
                list.add(Node.val);

                for(Node n : Node.children){
                    if(n != null) queue.offer(n);
                }
            }
            result.add(list);
        }
        return result;
    }
}

