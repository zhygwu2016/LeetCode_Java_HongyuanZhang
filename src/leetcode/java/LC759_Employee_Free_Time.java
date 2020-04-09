package leetcode.java;

// LC759. Employee Free Time
// https://leetcode.com/problems/insert-interval/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

// 所有intervals start点和end点放在一起排序
// 遇见start +1, 遇见end -1.
// 得0时，就找到了都空余的时间
// Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
//          1 2 2 3 4 5 6 7 9 12
//          S S S E E E S E S E
// balance:  1 2 3 2 1 0 1 0 1 0
// 所以[5, 6] [7, 9]是我们找到结果
public class LC759_Employee_Free_Time {
    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee: avails)
            for (Interval iv: employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
        for (int[] event: events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0)
                ans.add(new Interval(prev, event[0]));
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
}

// 也可以所有interval按左端点排序。一个一个merge，找出gap。
