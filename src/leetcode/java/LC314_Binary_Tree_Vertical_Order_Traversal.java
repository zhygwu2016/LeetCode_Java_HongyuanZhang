package leetcode.java;

// 314. Binary Tree Vertical Order Traversal
// https://leetcode.com/problems/binary-tree-vertical-order-traversal/

import javax.swing.tree.TreeNode;
import java.util.*;

public class LC314_Binary_Tree_Vertical_Order_Traversal {
}

/*
public class LC314_Binary_Tree_Vertical_Order_Traversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null) return result;

        // Use two queues to map the node with col
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        // Use a map to map the col with list
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Use two index to track the range of cols
        int min = 0, max = 0;

        nodes.offer(root);
        cols.offer(0);

        while(!nodes.isEmpty()){
            TreeNode node = nodes.poll();
            int col = cols.poll();

            if(!map.containsKey(col)){
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);

            if(node.left != null){
                nodes.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }

            if(node.right != null){
                nodes.offer(node.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for(int i = min; i <= max; i++){
            result.add(map.get(i));
        }
        return result;
    }
}

 */
