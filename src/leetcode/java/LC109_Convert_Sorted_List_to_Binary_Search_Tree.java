package leetcode.java;

// 109. Convert Sorted List to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 算法加强
// Time: O(n)
class LC109 {

    private ListNode root;

    public TreeNode sortedListToBST(ListNode head) {
        int size = 0;
        ListNode cur = head;
        root = head;

        while (cur != null) {
            cur = cur.next;
            size++;
        }

        return helper(0, size - 1);
    }

    private TreeNode helper(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode leftSubTree = helper(start, mid - 1);

        TreeNode curRoot = new TreeNode(root.val);
        curRoot.left = leftSubTree;

        root = root.next;

        TreeNode rightSubTree = helper(mid + 1, end);
        curRoot.right = rightSubTree;

        return curRoot;
    }
}


// Time: O(NlogN)
public class LC109_Convert_Sorted_List_to_Binary_Search_Tree {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;

        return constructBST(0, checkLength(head) - 1, head);
    }

    private int checkLength(ListNode head){
        if(head == null) return 0;
        return checkLength(head.next) + 1;
    }

    private TreeNode constructBST(int start, int end, ListNode head){
        if(start > end) return null;

        int mid = start + (end - start) / 2;
        ListNode temp = head;

        for(int i = 0; i < mid; i++){
            temp = temp.next;
        }

        TreeNode root = new TreeNode(temp.val);
        root.left = constructBST(start, mid - 1, head);
        root.right = constructBST(mid + 1, end, head);

        return root;
    }
}

