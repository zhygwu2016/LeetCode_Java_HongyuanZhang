package leetcode.java;

// 206. Reverse Linked List
// https://leetcode.com/problems/reverse-linked-list/

public class LC206_Reverse_Linked_List {

}

/*

// 1. iteration
public class LC206_Reverse_Linked_List {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode cur = head;
        ListNode post = cur.next;

        while(cur != null){
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }
}

// 2. recursion
class LC206_recursion {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

 */
