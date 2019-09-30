package leetcode.java;

// 86. Partition List
// https://leetcode.com/problems/partition-list/

public class LC86_Partition_List {
}

/*
public class LC86_Partition_List {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;

        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);

        ListNode p1 = small;
        ListNode p2 = large;

        while(head != null){
            if(head.val < x){
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }

        p2.next = null;
        p1.next = large.next;

        return small.next;
    }
}

 */
