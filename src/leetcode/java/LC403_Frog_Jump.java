package leetcode.java;

// LC403. Frog Jump
// https://leetcode.com/problems/frog-jump/

import java.util.HashMap;

// DFS + pruning
public class LC403_Frog_Jump {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length <= 1)  return false;
        if (stones[1] - stones[0] != 1) return false;

        HashMap<Integer, Boolean>[] mem = new HashMap[stones.length];
        for (int i = 0; i < stones.length; i++) {
            mem[i] = new HashMap<Integer, Boolean>();
        }

        return search(stones, 1, 1, mem);
    }

    private boolean search(int[] stones, int idx, int k, HashMap<Integer, Boolean>[] mem){
        HashMap<Integer, Boolean> map = mem[idx];
        if (map.containsKey(k)) return map.get(k);

        int len = stones.length;
        if (idx == len - 1) return true;

        for (int i = idx + 1; i < len; i++) {
            int dis = stones[i] - stones[idx];
            if (dis < k - 1) continue;
            if (dis > k + 1) break;

            if (dis == k - 1 || dis == k || dis == k + 1) {
                if (search(stones, i, dis, mem)) {
                    map.put(k, true);
                    return true;
                }
            }
        }

        map.put(k, false);
        return false;
    }
}

//Time: Pruning: O(N * K * 1) N是stones长度
//这道题可以pruning 不能DP(不太好DP)
//因为搜索状态是离散的  而DP添值是可连续的（比如这个题里的k）
//搜索状态不连续的话 DP不太make sense


