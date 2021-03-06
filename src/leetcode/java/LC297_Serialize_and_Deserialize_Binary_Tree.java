package leetcode.java;

// 297. Serialize and Deserialize Binary Tree
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LC297_Serialize_and_Deserialize_Binary_Tree {

    // Encodes a tree to a single string.
    // Use Level Order to encode, add null to maker it a "complete" tree
    public String serialize(TreeNode root) {
        if(root == null) return null;
        final StringBuilder sb = new StringBuilder();
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp != null){
                sb.append(temp.val + ",");
                queue.offer(temp.left);
                queue.offer(temp.right);
            } else {
                sb.append("null" + ",");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // Use Level Order to encode
    public TreeNode deserialize(String data) {
        if(data == null) return null;
        final String[] values = data.split(",");
        final Queue<TreeNode> queue = new LinkedList<>();
        int counter = 0;
        TreeNode root = new TreeNode(Integer.parseInt(values[counter++]));
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            // Recover the left node
            if(counter < values.length){
                String left = values[counter++];
                if(!left.equals("null")){
                    temp.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(temp.left);
                }
            }
            // Recover the right node
            if(counter < values.length){
                String right = values[counter++];
                if(!right.equals("null")){
                    temp.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(temp.right);
                }
            }
        }
        return root;
    }
}

// DFS pre-order
class LC297_DFS_PreOrder {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q);
    }

    public TreeNode deserialize(Queue<String> q) {
        String s = q.poll();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserialize(q);
        root.right = deserialize(q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


