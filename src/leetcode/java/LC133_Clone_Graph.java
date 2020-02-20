package leetcode.java;

// LC133. Clone Graph
// https://leetcode.com/problems/clone-graph/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

 */

// BFS
public class LC133_Clone_Graph {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph(Node Node) {
        if (Node == null) {
            return Node;
        }

        // If the Node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(Node)) {
            return visited.get(Node);
        }

        // Create a clone for the given Node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(Node.val, new ArrayList());
        // The key is original Node and value being the clone Node.
        visited.put(Node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned Node.
        for (Node neighbor: Node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}

// BFS
class LC133_BFS {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node Node) {
        if (Node == null) {
            return Node;
        }

        // Hash map to save the visited Node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<Node, Node> visited = new HashMap();

        // Put the first Node in the queue
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(Node);
        // Clone the Node and put it in the visited dictionary.
        visited.put(Node, new Node(Node.val, new ArrayList()));

        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a Node say "n" from the from the front of the queue.
            Node n = queue.remove();
            // Iterate through all the neighbors of the Node "n"
            for (Node neighbor: n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // Clone the neighbor and put in the visited, if not present already
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    // Add the newly encountered Node to the queue.
                    queue.add(neighbor);
                }
                // Add the clone of the neighbor to the neighbors of the clone Node "n".
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        // Return the clone of the Node from visited.
        return visited.get(Node);
    }
}
