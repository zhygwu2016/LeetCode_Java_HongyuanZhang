package leetcode.java;

// 108. Convert Sorted Array to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

import javax.swing.tree.TreeNode;

public class LC108_Convert_Sorted_Array_to_Binary_Search_Tree {
}

/*
public class LC108_Convert_Sorted_Array_to_Binary_Search_Tree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;

        return constructBST(0, nums.length - 1, nums);
    }

    private TreeNode constructBST(int start, int end, int[] nums){
        if(start > end) return null;
        final int mid = start + (end - start) / 2;

        TreeNode cur = new TreeNode(nums[mid]);

        cur.left = constructBST(start, mid - 1, nums);
        cur.right = constructBST(mid + 1, end, nums);

        return cur;
    }
}

 */
