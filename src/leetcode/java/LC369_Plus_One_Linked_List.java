package leetcode.java;

// 369. Plus One Linked List
// https://leetcode.com/problems/plus-one-linked-list/

public class LC369_Plus_One_Linked_List {
}

/*
public class LC369_Plus_One_Linked_List {
    public ListNode plusOne(ListNode head) {
        if(head == null) return null;

        ListNode reverseHead = reverseList(head);
        addOne(reverseHead);

        return reverseList(reverseHead);
    }

    private ListNode reverseList(ListNode head){
        ListNode pre = null, cur = head, post = cur.next;

        while(cur != null){
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }

    private void addOne(ListNode head){
        ListNode cur = head;
        ListNode pre = null;

        int carry = 1;

        while(cur != null){
            int sum = cur.val + carry;
            carry = sum >= 10 ? 1 : 0;
            if(sum < 10){
                cur.val = sum;
                break;
            } else {
                cur.val = 0;
                pre = cur;
                cur = cur.next;
            }
        }

        if(carry == 1){
            pre.next = new ListNode(1);
        }
    }
}

 */
