package leetcode.java;

// 373. Find K Pairs with Smallest Sums
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/

import java.util.*;

public class LC373_Find_K_Pairs_with_Smallest_Sums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ret = new ArrayList<>();

        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return ret;
        }

        int len1 = nums1.length, len2 = nums2.length;

        HashSet<Cell> set = new HashSet<>();

        PriorityQueue<Cell> queue = new PriorityQueue<Cell>(new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2) {
                return c1.val - c2.val;
            }
        });

        Cell c = new Cell(nums1[0] + nums2[0], 0, 0);
        queue.offer(c);
        set.add(c);

        while (k-- > 0) {
            if (queue.isEmpty()) break; // k可能很大很大

            Cell cur = queue.poll();
            int i = cur.i, j = cur.j;
            List<Integer> list = new ArrayList<>();
            list.add(nums1[i]);
            list.add(nums2[j]);
            ret.add(list);
            if (i + 1 < len1) {
                Cell c1 = new Cell(nums1[i + 1] + nums2[j], i + 1, j);
                if (!set.contains(c1)) {
                    queue.offer(c1);
                    set.add(c1);
                }
            }
            if (j + 1 < len2) {
                Cell c2 = new Cell(nums1[i] + nums2[j + 1], i, j + 1);
                if (!set.contains(c2)) {
                    queue.offer(c2);
                    set.add(c2);
                }
            }
        }

        return ret;
    }

    class Cell {
        int val, i, j;

        public Cell(int val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return this.i * 31 + this.j;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Cell) {
                Cell c = (Cell)o;
                return this.i == c.i && this.j == c.j;
            } else {
                return false;
            }
        }
    }
}
