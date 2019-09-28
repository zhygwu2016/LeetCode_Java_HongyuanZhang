package leetcode.java;

// 142. Linked List Cycle II
// https://leetcode.com/problems/linked-list-cycle-ii/

/*
                 n
       --------------- ----------
       1 -> 2 -> 3 -> 4 -> 5 -> 6
                      |         |
                      -----------

                      s
                      f
                      t
 */
public class LC142_Linked_List_Cycle_II {

}
/*
public class LC142_Linked_List_Cycle_II {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) break;
        }

        if(slow != fast) return null;

        ListNode temp = head;

        while(temp != slow){
            temp = temp.next;
            slow = slow.next;
        }

        return slow;
    }
}

 */
