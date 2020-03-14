package leetcode.java;

// LC294. Flip Game II
// https://leetcode.com/problems/flip-game-ii/

import java.util.HashMap;

public class LC294_Flip_Game_II {
    public boolean canWin(String s) {
        char[] cs = s.toCharArray();
        return canWin(cs, new HashMap<String, Boolean>());
    }

    private boolean canWin(char[] cs, HashMap<String, Boolean> map) {
        int len = cs.length;
        String str = new String(cs);
        if (map.containsKey(str))  return map.get(str);

        for (int i = 0; i < len - 1; i++) {
            if (cs[i] == '+' && cs[i + 1] == '+') {
                cs[i] = '-';
                cs[i + 1] = '-';

                boolean res = canWin(cs, map);
                cs[i] = '+';
                cs[i + 1] = '+';

                if (!res) {
                    map.put(new String(str), true);
                    return true;
                }
            }
        }

        map.put(new String(str), false);
        return false;
    }
}

//对于每一个能flip的slot，可以选择flip，也可以选择不flip
//Time: O(2^n) n是board长度

