package leetcode.java;

// 143. Reorder List
// https://leetcode.com/problems/reorder-list/

public class LC143_Reorder_List {

    public void reorderList(ListNode head) {
        if(head == null || head.next ==null) return;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow.next;
        slow.next = null;
        ListNode secondHead = reverseList(temp);

        ListNode firstHead = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        temp = dummy;

        while(firstHead != null && secondHead != null){
            temp.next = firstHead;
            firstHead = firstHead.next;
            temp = temp.next;

            temp.next = secondHead;
            secondHead = secondHead.next;
            temp = temp.next;
        }

        if(firstHead != null){
            temp.next = firstHead;
        }else{
            temp.next = secondHead;
        }

    }

    private ListNode reverseList(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode pre = null, cur = head, post = cur.next;

        while(cur != null){
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }

        return pre;
    }
}

