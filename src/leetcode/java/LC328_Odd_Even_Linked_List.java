package leetcode.java;

// 328. Odd Even Linked List
// https://leetcode.com/problems/odd-even-linked-list/

public class LC328_Odd_Even_Linked_List {
}

/*
public class LC328_Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode odd = head, even = head.next, evenHead = even;

        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }
}

 */
