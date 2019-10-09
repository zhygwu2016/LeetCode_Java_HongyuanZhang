package leetcode.java;

// 337. House Robber III
// https://leetcode.com/problems/house-robber-iii/

// https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class LC337_House_Robber_III {
}

/*
public class LC337_House_Robber_III {
    public int rob(TreeNode root) {
        if(root == null) return 0;

        int val = 0;

        if(root.left != null){
            val += rob(root.left.left) + rob(root.left.right);
        }

        if(root.right != null){
            val += rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }
}

public class LC337_solution_2 {
    public int rob(TreeNode root) {
        return robSub(root, new HashMap<>());
    }

    private int robSub(TreeNode root, Map<TreeNode, Integer> map){
        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root);

        int val = 0;

        if(root.left != null){
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }

        if(root.right != null){
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }

        val =  Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);

        return val;
    }
}


// solution 3
// Redefine rob(root) as a new function which will return an array of two elements,
// the first element of which denotes the maximum amount of money that
// can be robbed if root is not robbed, while the second element signifies
// the maximum amount of money robbed if it is robbed.
public class LC337_solution_3 {
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root){
        if(root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}

 */
