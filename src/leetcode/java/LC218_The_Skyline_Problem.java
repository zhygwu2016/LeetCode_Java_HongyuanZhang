package leetcode.java;

// LC218. The Skyline Problem
// https://leetcode.com/problems/the-skyline-problem/

import java.util.*;
/*
算法加强

左右端点放在一起排序。从左往右扫：

左端点放到数据结构里。（数据结构里放height）如果放的点的高度是最大值，这个点就是res之一；
遇到右端点，在数据结构里删掉这个点的高度；如果删的是数据结构里的最大值，用此点，高度是删完之后当前高度的最大值。

用什么数据结构：maxHeap

排序时注意端点index相同，高度不同的几种情况
 */

public class LC218_The_Skyline_Problem {

    private class EndPoint implements Comparable<EndPoint> {
        public int x, height;
        public boolean isEnd;

        public EndPoint(int x, int height, boolean isEnd) {
            this.x = x;
            this.height = height;
            this.isEnd = isEnd;
        }

        @Override
        public int compareTo(EndPoint that) {
            if (this.x != that.x) {
                return this.x - that.x;
            } else {
                if (!this.isEnd && !that.isEnd) { // both left points: high first
                    return that.height - this.height;
                } else if (this.isEnd && that.isEnd) { // both right points: low first
                    return this.height - that.height;
                } else { // one left and one right: left point first
                    return this.isEnd ? 1 : -1;
                }
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<EndPoint> endPoints = new ArrayList<EndPoint>();
        for (int[] building : buildings) {
            endPoints.add(new EndPoint(building[0], building[2], false));
            endPoints.add(new EndPoint(building[1], building[2], true));
        }

        Collections.sort(endPoints);

        // max heap
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(buildings.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });

        for (EndPoint ep : endPoints) {
            if (!ep.isEnd) { // left point
                if (heap.isEmpty() || ep.height > heap.peek()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(ep.x);
                    list.add(ep.height);
                    res.add(list);
                }
                heap.offer(ep.height);
            } else { // right point
                heap.remove(ep.height);
                if (heap.isEmpty() || ep.height > heap.peek()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(ep.x);
                    list.add(heap.isEmpty() ? 0 : heap.peek());
                    res.add(list);
                }
            }
        }

        return res;
    }
}
