package leetcode.java;

// 19. Remove Nth Node From End of List
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


// fast先跑k个距离。然后slow fast齐步走。走到最后，slow是位置

public class LC19_Remove_Nth_Node_From_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode slow = head;
        ListNode fast = head;

        while (n-- > 1) {
            fast = fast.next;
        }

        while (fast.next != null) {
            pre = pre.next;
            slow = slow.next;
            fast = fast.next;
        }

        ListNode post = slow.next;
        pre.next = post;

        return dummy.next;
    }
}

