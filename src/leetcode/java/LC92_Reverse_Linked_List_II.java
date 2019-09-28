package leetcode.java;

// 92.  Reverse Linked List II
// https://leetcode.com/problems/reverse-linked-list-ii/

/*
      dummy -> 1     2 -> 3 -> 4    5 -> null
              p1    c1
                               p2   c2
                               temp
 */

public class LC92_Reverse_Linked_List_II {
}
/*
public class LC92_Reverse_Linked_List_II {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre1 = dummy, cur1 = head;
        for(int i = 1; i < m; i++){
            pre1 = cur1;
            cur1 = cur1.next;
        }

        pre1.next = null;
        ListNode pre2 = cur1, cur2 = cur1.next;
        for(int i = 0; i < n - m; i++){
            pre2 = cur2;
            cur2 = cur2.next;
        }

        pre2.next = null;

        ListNode temp = reverseLinkedList(cur1);

        pre1.next = temp;
        cur1.next = cur2;

        return dummy.next;
    }

    private ListNode reverseLinkedList(ListNode head){
        ListNode pre = null, cur = head; post = cur.next;

        while(cur != null){
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }

        return pre;
    }
}

 */
