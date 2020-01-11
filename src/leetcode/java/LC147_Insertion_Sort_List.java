package leetcode.java;

// 147. Insertion Sort List
// https://leetcode.com/problems/insertion-sort-list/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class LC147_Insertion_Sort_List {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = head.next, prev = head;

        while(cur != null){
            if(prev.val > cur.val){
                ListNode temp = fetchNode(prev, cur);
                insert(dummy, temp);
                cur = prev;
            }

            prev = cur;
            cur = cur.next;
        }

        return dummy.next;
    }

    private ListNode fetchNode(ListNode prev, ListNode cur){
        prev.next = cur.next;
        cur.next = null;
        return cur;
    }

    private void insert(ListNode dummy, ListNode node){
        ListNode prev = dummy, cur = dummy.next;

        while(cur.val < node.val){
            prev = cur;
            cur = cur.next;
        }

        node.next = cur;
        prev.next = node;
    }
}

