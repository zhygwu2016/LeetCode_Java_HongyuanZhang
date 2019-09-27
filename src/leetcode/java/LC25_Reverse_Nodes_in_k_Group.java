package leetcode.java;

// 25. Reverse Nodes in k-Group
// https://leetcode.com/problems/reverse-nodes-in-k-group/

public class LC25_Reverse_Nodes_in_k_Group {

}

/*
public class LC25_Reverse_Nodes_in_k_Group {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = head;
        int count = 0;

        while(pre != null && count < k){
            pre = pre.next;
            count++;
        }

        if(count == k){
            pre = reverseKGroup(pre, k);

            ListNode cur = head, post = head.next;

            while(count-- > 0){
                post = cur.next;
                cur.next = pre;
                pre = cur;
                cur = post;
            }

            head = pre;
        }
        return head;
    }
}

 */
