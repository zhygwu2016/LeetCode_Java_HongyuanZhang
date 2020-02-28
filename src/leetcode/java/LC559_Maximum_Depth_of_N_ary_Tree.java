package leetcode.java;

// 559. Maximum Depth of N-ary Tree
// https://leetcode.com/problems/maximum-depth-of-n-ary-tree/

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC559_Maximum_Depth_of_N_ary_Tree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        if(root == null) return 0;

        int depth = 0;

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            while(size-- > 0){
                Node temp = queue.poll();
                for(Node n : temp.children){
                    if(n != null) queue.offer(n);
                }
            }
            depth++;
        }
        return depth;
    }
}

class LC559{
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        if(root == null) return 0;

        int max = 0;

        for(Node n : root.children){
            max = Math.max(max, maxDepth(n));
        }
        return max + 1;
    }
}

