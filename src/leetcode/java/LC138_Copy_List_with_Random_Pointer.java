package leetcode.java;
// LC138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/

import java.util.HashMap;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
// space O(n), recursion
public class LC138_Copy_List_with_Random_Pointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };
    HashMap<Node, Node> visited = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        if (this.visited.containsKey(head)) {
            return this.visited.get(head);
        }

        Node node = new Node(head.val, null, null);
        this.visited.put(head, node);

        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);

        return node;
    }
}

// space O(n), Iterative
class LC138_Copy_List_with_Random_Pointer_2 {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };
    // Visited dictionary to hold old node reference as "key"
    // and new node reference as the "value"
    HashMap<Node, Node> visited = new HashMap<Node, Node>();

    public Node getClonedNode(Node node) {
        // If the node exists then
        if (node != null) {
            // Check if the node is in the visited dictionary
            if (this.visited.containsKey(node)) {
                // If its in the visited dictionary then
                // return the new node reference from the dictionary
                return this.visited.get(node);
            } else {
                // Otherwise create a new node, add to the dictionary and return it
                this.visited.put(node, new Node(node.val, null, null));
                return this.visited.get(node);
            }
        }
        return null;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node oldNode = head;

        // Creating the new head node.
        Node newNode = new Node(oldNode.val, null, null);
        this.visited.put(oldNode, newNode);

        // Iterate on the linked list until all nodes are cloned.
        while (oldNode != null) {
            // Get the clones of the nodes referenced by random and next pointers.
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            // Move one step ahead in the linked list.
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }
}

// O(1) Space
class LC138_Copy_List_with_Random_Pointer_3 {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val, null, null);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }
}
