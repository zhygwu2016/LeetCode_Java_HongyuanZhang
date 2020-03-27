package leetcode.java;

// LC117. Populating Next Right Pointers in Each Node II
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

import java.util.LinkedList;
import java.util.Queue;

// level order traverse
// Not constant space
public class LC117_Populating_Next_Right_Pointers_in_Each_Node_II {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> Q = new LinkedList<Node>();
        Q.add(root);

        while (!Q.isEmpty()) {
            int size = Q.size();

            for(int i = 0; i < size; i++) {
                Node node = Q.poll();
                if (i < size - 1) {
                    node.next = Q.peek();
                }
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        // Since the tree has now been modified, return the root node
        return root;
    }
}

class LC117 {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    Node prev, leftmost;
    // leftmost: 当前层最左边Node
    // prev: 下一层的上一个childNode
    // 方法：走这一层，用processChild()链接下一层相邻的childNode

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // The root node is the only node on the first level
        // and hence its the leftmost node for that level
        this.leftmost = root;

        Node curr = leftmost;

        while (this.leftmost != null) {

            // "prev" tracks the latest node on the "next" level
            // while "curr" tracks the latest node on the current level
            this.prev = null;
            curr = this.leftmost;

            // We reset this so that we can re-assign it to the leftmost
            // node of the next level. Also, if there isn't one, this
            // would help break us out of the outermost loop.
            this.leftmost = null;

            // Iterate on the nodes in the current level using
            // the next pointers already established.
            while (curr != null) {

                // Process both the children and update the prev
                // and leftmost pointers as necessary.
                this.processChild(curr.left);
                this.processChild(curr.right);

                curr = curr.next;
            }
        }

        return root ;
    }

    public void processChild(Node childNode) {

        if (childNode != null) {

            // If the "prev" pointer is already set, setup its next pointer
            if (this.prev != null) {
                this.prev.next = childNode;

            } else {
                // else it means this child node is the first node
                this.leftmost = childNode;
            }

            this.prev = childNode;
        }
    }
}
