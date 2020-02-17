package leetcode.java;

// LC295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/

import java.util.Collections;
import java.util.PriorityQueue;

public class LC295_Find_Median_from_Data_Stream {

    /** initialize your data structure here. */

    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private boolean even = true;

    public void addNum(int num) {
        if (even) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (even)
            return (small.peek() + large.peek()) / 2.0;
        else
            return small.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

// https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups
/*
1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    We can maintain an integer array of length 100 to store the count of each number
    along with a total count. Then, we can iterate over the array to find the middle value
    to get our median.
    Time and space complexity would be O(100) = O(1).

2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
    In this case, we need an integer array of length 100 and a hashmap for these numbers that are not in [0,100].

    I dont' think we need hashmap.
    As 99% is between 0-100. So can keep a counter for less_than_hundred and greater_than_hundred.
    As we know soluiton will be definately in 0-100
    we don't need to know those number which are >100 or <0, only count of them will be enough.
 */
