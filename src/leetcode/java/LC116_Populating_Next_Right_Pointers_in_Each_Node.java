package leetcode.java;

// 116. Populating Next Right Pointers in Each Node
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

import java.util.LinkedList;
import java.util.Queue;

public class LC116_Populating_Next_Right_Pointers_in_Each_Node {
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

class LC116 {
    public Node connect(Node root) {

        if (root == null) {
            return root;
        }

        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;

        // Once we reach the final level, we are done
        while (leftmost.left != null) {

            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the
            // corresponding links for the next level
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Progress along the list (nodes on the current level)
                head = head.next;
            }

            // Move onto the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}
