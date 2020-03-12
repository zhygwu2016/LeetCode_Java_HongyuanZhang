package leetcode.java;

// LC281. Zigzag Iterator
// https://leetcode.com/problems/zigzag-iterator/

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC281_Zigzag_Iterator {
}

public class ZigzagIterator {

    Queue<List<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.queue = new LinkedList<>();
        if (v1 != null && !v1.isEmpty()) {
            queue.offer(v1);
        }
        if (v2 != null && !v2.isEmpty()) {
            queue.offer(v2);
        }
    }

    public ZigzagIterator(List<List<Integer>> lists){
        this.queue = new LinkedList<>();
        for (List<Integer> list : lists) {
            if (list != null && !list.isEmpty()) {
                queue.offer(list);
            }
        }
    }

    public int next() {
        List<Integer> curList = queue.poll();
        int ret = curList.remove(0);
        if (!curList.isEmpty()) {
            this.queue.offer(curList);
        }
        return ret;
    }

    public boolean hasNext() {
        return !this.queue.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
