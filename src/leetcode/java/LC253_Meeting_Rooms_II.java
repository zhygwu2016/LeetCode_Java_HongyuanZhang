package leetcode.java;

// LC253. Meeting Rooms II
// https://leetcode.com/problems/meeting-rooms-ii/

import java.lang.reflect.Array;
import java.util.*;

// Using minHeap
public class LC253_Meeting_Rooms_II {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(intervals.length, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });

        // Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(final int[] a, final int[] b) {
                return a[0] - b[0];
            }
        });

        // Add the first meeting
        allocator.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }
}

// 算法哥
// 左右端点放在一起排序
class LC253 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null) return 0;

        List<EndPoint> list = new ArrayList<EndPoint>();
        for (int[] interval : intervals) {
            list.add(new EndPoint(interval[0], false));
            list.add(new EndPoint(interval[1], true));
        }

        Collections.sort(list);

        int count = 0, max = 0;
        for (EndPoint ep : list) {
            if (!ep.isEnd) {
                count++;
            } else {
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }


    class EndPoint implements Comparable<EndPoint> {
        public int val;
        public boolean isEnd;

        public EndPoint(int val, boolean isEnd) {
            this.val = val;
            this.isEnd = isEnd;
        }

        public int compareTo(EndPoint ep) {
            if (this.val < ep.val) {
                return -1;
            } else if (this.val > ep.val) {
                return 1;
            } else {
                if (this.isEnd) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
