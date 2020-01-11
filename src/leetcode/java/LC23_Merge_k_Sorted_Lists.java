package leetcode.java;

// 23. Merge k Sorted Lists
// https://leetcode.com/problems/merge-k-sorted-lists/

import java.util.Comparator;
import java.util.PriorityQueue;


public class LC23_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });

        for(ListNode l : lists){
            if(l != null) pq.offer(l);
        }

        while(!pq.isEmpty()){
            ListNode top = pq.poll();
            cur.next = top;
            top = top.next;

            if(top != null){
                pq.offer(top);
            }
            cur = cur.next;
        }

        ListNode result = dummy.next;
        dummy.next = null;
        return result;
    }
}

