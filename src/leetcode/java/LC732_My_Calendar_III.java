package leetcode.java;

// LC732. My Calendar III
// https://leetcode.com/problems/my-calendar-iii/

import java.util.TreeMap;

// Key: start或end
// delta, 遇到start加1，遇到end减1
// 遍历delta.values()的最大值就是我们要的K
// Time Complexity: O(N^2), where N is the number of events booked.
// For each new event, we traverse delta in O(N) time.
// Space Complexity: O(N)O(N), the size of delta.
public class LC732_My_Calendar_III {

    TreeMap<Integer, Integer> delta;

    public LC732_My_Calendar_III() {
        delta = new TreeMap<>();
    }

    public int book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0, max = 0;

        for (int d: delta.values()) {
            active += d;
            max = Math.max(max, active);
        }

        return max;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
