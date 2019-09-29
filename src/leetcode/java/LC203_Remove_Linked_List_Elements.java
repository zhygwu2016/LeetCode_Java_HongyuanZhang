package leetcode.java;

// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/

public class LC203_Remove_Linked_List_Elements {
}

/*
public class LC203_Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;

        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}

 */
