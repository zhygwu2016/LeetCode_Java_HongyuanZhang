package leetcode.java;

// LC731. My Calendar II
// https://leetcode.com/problems/my-calendar-ii/

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// Brute Force
// 同时记录一个List<int[]> overlaps 发生double booking的区间
// Time: O(N^2)
public class LC731_My_Calendar_II {

    List<int[]> calendar;
    List<int[]> overlaps;

    public LC731_My_Calendar_II() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] overlap : overlaps) {
            if (start < overlap[1] && end > overlap[0]) {
                return false;
            }
        }

        for (int[] event : calendar) {
            if (start < event[1] && end > event[0]) {
                overlaps.add(new int[]{Math.max(start, event[0]), Math.min(end, event[1])});
            }
        }

        calendar.add(new int[]{start, end});
        return true;
    }
}

// Time Complexity: O(N^2), where N is the number of events booked.
// For each new event, we traverse delta in O(N) time.
// Space Complexity: O(N), the size of delta.
class MyCalendarTwo {
    TreeMap<Integer, Integer> delta;

    public MyCalendarTwo() {
        delta = new TreeMap();
    }

    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d: delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0)
                    delta.remove(start);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
