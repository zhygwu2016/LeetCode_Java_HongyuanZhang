package leetcode.java;

// 23. Merge k Sorted Lists
// https://leetcode.com/problems/merge-k-sorted-lists/

import java.util.Comparator;
import java.util.PriorityQueue;


public class LC23_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });

        for (ListNode l : lists) {
            if(l != null) pq.offer(l);
        }

        while (!pq.isEmpty()) {
            ListNode top = pq.poll();
            cur.next = top;
            top = top.next;

            if (top != null) {
                pq.offer(top);
            }

            cur = cur.next;
        }

        ListNode result = dummy.next;
        dummy.next = null;
        return result;
    }
}

// Divide and Conquer
// https://leetcode.com/problems/merge-k-sorted-lists/discuss/152022/Divide-and-Conquer-Heap-with-Explanations
class LC23_DivideAndConquer {
    public ListNode mergeKLists(ListNode[] lists) {
        // Corner cases.
        if (lists == null || lists.length == 0)
            return null;

        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        // Base cases.
        if (end < start) {
            return null;
        }
        if (end - start == 0) {
            return lists[start];
        }
        if (end - start == 1) {
            return mergeTwoLists(lists[start], lists[end]);
        }

        // Divide lists into 2 sublists and sort them as a whole recursively.
        int mid = start + ((end - start) >> 1);
        ListNode lower = mergeKLists(lists, start, mid);
        ListNode upper = mergeKLists(lists, mid + 1, end);

        return mergeTwoLists(lower, upper);
    }

    private ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0), ptr = dummyHead;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                ptr.next = head1;
                head1 = head1.next;
            } else {
                ptr.next = head2;
                head2 = head2.next;
            }
            ptr = ptr.next;
        }
        if (head1 != null) {
            ptr.next = head1;
        } else if (head2 != null) {
            ptr.next = head2;
        }
        return dummyHead.next;
    }
}

