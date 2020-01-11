package leetcode.java;

// 437. Path Sum III
// https://leetcode.com/problems/path-sum-iii/

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

