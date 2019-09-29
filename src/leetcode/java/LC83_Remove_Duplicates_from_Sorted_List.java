package leetcode.java;

// 83. Remove Duplicates from Sorted List
// https://leetcode.com/problems/remove-duplicates-from-sorted-list/

public class LC83_Remove_Duplicates_from_Sorted_List {
}

/*
public class LC83_Remove_Duplicates_from_Sorted_List {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode slow = head, fast = head.next;

        while(slow != null){
            fast = slow.next;
            while(fast != null && slow.val == fast.val){
                fast = fast.next;
            }
            slow.next = fast;
            slow = slow.next;
        }
        return head;
    }
}

 */
