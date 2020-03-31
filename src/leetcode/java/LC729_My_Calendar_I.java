package leetcode.java;

// LC729. My Calendar I
// https://leetcode.com/problems/my-calendar-i/

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// Brute Force
// Time: O(N^2) Space: O(N)
public class LC729_My_Calendar_I {

    List<int[]> calendar;

    public LC729_My_Calendar_I() {
        calendar = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] event : calendar) {
            if (start < event[1] && end > event[0]) {
                return false;
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}


// Using TreeMap
// Time: O(NlogN)
class MyCalendar_I {

    // Key: start
    // Value: end
    TreeMap<Integer, Integer> calendar;

    public MyCalendar_I() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);

        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }

        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
