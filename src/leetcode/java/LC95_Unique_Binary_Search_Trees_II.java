package leetcode.java;

// LC95. Unique Binary Search Trees II
// https://leetcode.com/problems/unique-binary-search-trees-ii/

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LC95_Unique_Binary_Search_Trees_II {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0)  return new ArrayList<TreeNode>();
        return gen(1, n);
    }

    private List<TreeNode> gen(int start, int end){
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = gen(start, i - 1);
            List<TreeNode> right = gen(i + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
