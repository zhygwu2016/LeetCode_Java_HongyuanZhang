package leetcode.java;

// LC239. Sliding Window Maximum
// https://leetcode.com/problems/sliding-window-maximum/

import java.util.Deque;
import java.util.LinkedList;

public class LC239_Sliding_Window_Maximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] res= new int[len - k + 1];
        Deque<Integer> que = new LinkedList<Integer>(); // double-ended queue

        for (int i = 0; i < len; i++) {
            if (i >= k && que.getFirst() == i - k) {
                que.pollFirst();
            }

            // Same as Rectangle Question, remove the useless data from the queue
            // if nums[que.getLast()] < nums[i], then from now on,
            // nums[que.getLast()] must not be a max element. So remove it
            while (!que.isEmpty() && nums[que.getLast()] <= nums[i]) {
                que.pollLast();
            }
            que.offerLast(i);

            if (i >= k - 1) {
                res[i - k + 1] = nums[que.getFirst()];
            }
        }

        return res;
    }
}
