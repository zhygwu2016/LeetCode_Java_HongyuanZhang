package leetcode.java;

// LC428. Serialize and Deserialize N-ary Tree
// https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

import java.util.*;

public class LC428_Serialize_and_Deserialize_N_ary_Tree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");
            sb.append(root.children.size()).append(",");

            for (Node child : root.children) {
                buildString(child, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(q);
    }

    private Node buildTree(Queue<String> q) {
        String s = q.poll();
        if (s.equals("#")) return null;

        Node root = new Node(Integer.parseInt(s));
        int size = Integer.parseInt(q.poll());

        root.children = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            root.children.add(buildTree(q));
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
