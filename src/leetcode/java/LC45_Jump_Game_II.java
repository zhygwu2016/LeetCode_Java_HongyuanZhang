package leetcode.java;

// 45. Jump Game II
// https://leetcode.com/problems/jump-game-ii/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
// The main idea is based on greedy.
// Let's say the range of the current jump is [curBegin, curEnd],
// curFarthest is the farthest point that all points
// in [curBegin, curEnd] can reach.
// Once the current point reaches curEnd, then trigger another jump,
// and set the new curEnd with curFarthest, then keep the above steps, as the following:
public class LC45_Jump_Game_II {
    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for(int i = 0; i < nums.length - 1; i++){
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if(i == curEnd){
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}

// Greedy
class LC45_Jump_Game_II_Greedy {
    public int jump(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int minStep = 0;
        int prevRange = 0;
        int curRange = 0;

        for(int i = 0; i < nums.length; i++){
            if(curRange >= nums.length - 1) return minStep + 1;
            if(i > prevRange){
                prevRange = curRange;
                minStep++;
            }
            curRange = Math.max(curRange, nums[i] + i);
        }
        return minStep;
    }
}

// BFS
// Time Limit Exceeded
//public class LC45_Jump_Game_II {
//    public int jump(int[] nums) {
//        if(nums == null || nums.length == 0) return 0;
//        if(nums.length == 1) return 0;
//
//        Queue<Integer> queue = new LinkedList<>();
//        Set<Integer> visited = new HashSet<>();
//        queue.offer(0);
//        visited.add(0);
//
//        int level = 0;
//        int maxPos = 0;
//
//        while(!queue.isEmpty()){
//            int size = queue.size();
//
//            while(size-- > 0){
//                int curPos = queue.poll();
//                if(curPos >= nums.length - 1) return level;
//
//                for(int i = 1; i <= nums[curPos]; i++){
//                    if(visited.add(curPos + i)){
//                        queue.offer(curPos + i);
//                        maxPos = Math.max(maxPos, curPos + i);
//                    }
//                }
//            }
//            level++;
//        }
//
//        if(maxPos < nums.length - 1) return -1;
//        return level;
//    }
//}
