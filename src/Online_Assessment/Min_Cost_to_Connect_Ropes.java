package Online_Assessment;

// Min Cost to Connect Ropes
// https://leetcode.com/discuss/interview-question/344677

/*
Input: ropes = [8, 4, 6, 12]
Output: 58
Explanation: The optimal way to connect ropes is as follows
1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
3. Connect the ropes of length 18 and 12 (cost is 30).
Total cost to connect the ropes is 10 + 18 + 30 = 58
 */

// minheap, 最短的两个merge, merge后再放回heap, 直到最后heap size为1

import java.util.PriorityQueue;
import java.util.Queue;

public class Min_Cost_to_Connect_Ropes {
    private static int mergeFiles(int[] files) {
        Queue<Integer> minHeap = new PriorityQueue<Integer>();
        for(int f : files) {
            minHeap.offer(f);
        }
        int res = 0;
        while(minHeap.size() > 1) {
            int f1 = minHeap.poll();
            int f2 = minHeap.poll();
            int tmp = f1 + f2;
            res += tmp;
            minHeap.offer(tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] files1 = {8, 4, 6, 12};
        int[] files2 = {20, 4, 8, 2};
        int[] files3 = {1, 2, 5, 10, 35, 89};
        int[] files4 = {2, 2, 3, 3};
        System.out.println(mergeFiles(files1));
        System.out.println(mergeFiles(files2));
        System.out.println(mergeFiles(files3));
        System.out.println(mergeFiles(files4));
    }
}
