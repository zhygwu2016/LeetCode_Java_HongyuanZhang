package leetcode.java;

// 437. Path Sum III
// https://leetcode.com/problems/path-sum-iii/

import java.util.HashMap;
import java.util.Map;

public class LC437_Path_Sum_III {
    /*
        Typical recursive DFS.
        Space: O(n) due to recursion.
        Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree)
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;

        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode root, int sum){
        if(root == null) return 0;
        return (root.val == sum ? 1 : 0) + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
    }
}

/*
O(N)
prefix sum + HashMap
假设一条path是:  1 2 -1 -1 2
其prefix sum是: 1 3  2  1 3
我们要找path sum是2的path, 对于每个node，看HashMap中prefix sum - target是否存在
 */
class LC437 {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return findPathSum(root, 0, sum, map);
    }

    private int findPathSum(TreeNode curr, int sum, int target, Map<Integer, Integer> map) {
        if (curr == null) {
            return 0;
        }
        // update the prefix sum by adding the current val
        sum += curr.val;
        // get the number of valid path, ended by the current node
        int numPathToCurr = map.getOrDefault(sum-target, 0);
        // update the map with the current sum, so the map is good to be passed to the next recursion
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        // add the 3 parts discussed in 8. together
        int res = numPathToCurr + findPathSum(curr.left, sum, target, map)
                + findPathSum(curr.right, sum, target, map);
        // restore the map, as the recursion goes from the bottom to the top
        map.put(sum, map.get(sum) - 1);
        return res;
    }
}

