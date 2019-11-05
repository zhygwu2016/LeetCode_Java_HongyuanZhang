package leetcode.java;

// 113. Path Sum II
// https://leetcode.com/problems/path-sum-ii/

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class LC113_Path_Sum_II {
}

/*
public class LC113_Path_Sum_II {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        final List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        pathSum(root, sum, new ArrayList<Integer>(), result);

        return result;
    }

    private void pathSum(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result){
        if(root == null) return;

        if(root.left == null && root.right == null){
            if(sum == root.val){
                // Add and remove!
                list.add(root.val);
                result.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
        }

        // DFS moving deeper
        list.add(root.val);
        pathSum(root.left, sum - root.val, list, result);
        pathSum(root.right, sum - root.val, list, result);
        list.remove(list.size() - 1);
    }
}

 */
