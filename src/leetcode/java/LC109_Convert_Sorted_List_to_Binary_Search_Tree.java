package leetcode.java;

// 109. Convert Sorted List to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

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

