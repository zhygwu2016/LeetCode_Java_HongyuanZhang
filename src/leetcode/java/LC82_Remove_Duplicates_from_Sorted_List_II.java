package leetcode.java;

// 82. Remove Duplicates from Sorted List II
// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

public class LC82_Remove_Duplicates_from_Sorted_List_II {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;

        while(cur != null){
            while(cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
            }

            // If pre.next = cur, this means cur doesn't contains duplicates
            // else cur contains duplicates, pre.next should skip cur;
            if(pre.next == cur){
                // No duplicate n
                pre = cur;
            }else{
                pre.next = cur.next;
            }
            // Traverse to next node
            cur = cur.next;
        }
        return dummy.next;
    }
}

