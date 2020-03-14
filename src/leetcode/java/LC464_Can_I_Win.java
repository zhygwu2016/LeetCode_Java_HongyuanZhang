package leetcode.java;
// LC464. Can I Win
// https://leetcode.com/problems/can-i-win/

public class LC464_Can_I_Win {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger <= 0 || maxChoosableInteger > 20
                || desiredTotal <0 || desiredTotal > 300) {
            throw new IllegalArgumentException();
        }

        // if (1+2+3+...+maxChoosableInteger) 仍小于desiredTotal, 不可能true
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal)  return false;

        // boolean[] pool，表示1，2，3...maxChoosableInteger中的某个数是否被用过(true/false)
        // 因为题目说assume that maxChoosableInteger will not be larger than 20，
        // 所以可以用比特操作，这样才能够用mem进行Pruning
        int map = (1 << maxChoosableInteger) - 1;
        Boolean[] mem = new Boolean[map + 1];
        return helper(map, 0, desiredTotal, maxChoosableInteger, mem);
    }

    private boolean helper(int map, int curTotal, int desiredTotal,
                           int maxChoosableInteger, Boolean[] mem) {
        if (mem[map] != null) {
            return mem[map];
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((mask & map) != 0) {
                if (curTotal + i >= desiredTotal) {
                    return true;
                }

                int newMap = map - mask;
                if (!helper(newMap, curTotal + i, desiredTotal,
                        maxChoosableInteger, mem)) {
                    mem[map] = true;
                    return true;
                }
            }
        }

        mem[map] = false;
        return false;
    }
}

// Time: 指数。但是因为最长限制是20，可以看作是O(1)
