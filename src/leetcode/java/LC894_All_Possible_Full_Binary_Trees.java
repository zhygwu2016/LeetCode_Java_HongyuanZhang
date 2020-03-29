package leetcode.java;

// LC894. All Possible Full Binary Trees
// https://leetcode.com/problems/all-possible-full-binary-trees/

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
public class LC894_All_Possible_Full_Binary_Trees {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        for (int i = 1; i <= N - 2; i++) {
            List<TreeNode> leftList = allPossibleFBT(i);
            List<TreeNode> rightList = allPossibleFBT(N - i - 1);

            for (TreeNode l : leftList) {
                for (TreeNode r : rightList) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = l;
                    cur.right = r;
                    res.add(cur);
                }
            }
        }

        return res;
    }
}
