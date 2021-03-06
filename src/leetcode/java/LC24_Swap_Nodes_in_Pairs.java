package leetcode.java;

// 24. Swap Nodes in Pairs
// https://leetcode.com/problems/swap-nodes-in-pairs/

public class LC24_Swap_Nodes_in_Pairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode postHead = swapPairs(head.next.next);

        // Swap in pairs here, think carefully that the "next" is only a pointer
        head.next.next = head;
        ListNode newHead = head.next;
        head.next = postHead;

        return newHead;
    }
}

class LC24_Swap_Nodes_in_Pairs_2 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode cur = head;
        ListNode newHead = head.next;

        while(cur != null && cur.next != null){
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
            if(cur != null && cur.next != null) tmp.next = cur.next;
        }

        return newHead;
    }
}

