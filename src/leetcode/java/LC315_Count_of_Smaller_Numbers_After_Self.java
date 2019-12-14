package leetcode.java;

// LC315. Count of Smaller Numbers After Self
// https://leetcode.com/problems/count-of-smaller-numbers-after-self/

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;

public class LC315_Count_of_Smaller_Numbers_After_Self {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<Integer>();
        if (nums == null || nums.length == 0)  return res;
        int len = nums.length;
        TreeNode root = new TreeNode(nums[len - 1]);
        res.add(0);

        for (int i = len - 2; i >= 0; i--) {
            int val = nums[i];
            int ret = insert(root, val);
            res.add(0, ret);
        }

        return res;
    }

    private int insert(TreeNode root, int val) {
        TreeNode cur = root;
        int ret = 0;
        while (cur != null) {
            if (val < cur.val) {
                cur.leftSize++;
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                } else {
                    cur = cur.left;
                }
            } else if (val > cur.val) {
                ret += cur.leftSize + cur.selfCount;
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } else {
                    cur = cur.right;
                }
            } else {
                cur.selfCount++;
                ret += cur.leftSize;
                break;
            }

        }
        return ret;
    }

    class TreeNode {
        public int leftSize, selfCount;
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
            leftSize = 0;
            selfCount = 1;
        }
    }
}
