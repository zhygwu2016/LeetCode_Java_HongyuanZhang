package leetcode.java;

// LC488. Zuma Game
// https://leetcode.com/problems/zuma-game/

import java.util.HashMap;

// https://leetcode.com/problems/zuma-game/discuss/97027/StraightForward-Recursive-Java-Solution-beat-97
class LC488 {
    private int aux(String s, int[] c){
        if("".equals(s)) return 0;
        //worst case, every character needs 2 characters; plus one to make it impossible, ;-)
        int res = 2 * s.length() + 1;
        for(int i = 0; i < s.length();){
            int j = i++;
            while(i < s.length() && s.charAt(i) == s.charAt(j)) i++;
            int inc = 3 - i + j;
            if(c[s.charAt(j)] >= inc){
                int used = inc <= 0 ? 0 : inc;
                c[s.charAt(j)] -= used;
                int temp = aux(s.substring(0, j) + s.substring(i), c);
                if(temp >= 0) res = Math.min(res, used + temp);
                c[s.charAt(j)] += used;
            }
        }
        return res == 2 * s.length() + 1 ? -1 : res;
    }

    public int findMinStep(String board, String hand) {
        int[] c = new int[128];
        for(char x : hand.toCharArray()){
            c[x]++;
        }
        return  aux(board, c);
    }
}

public class LC488_Zuma_Game {

    private int min;

    public int findMinStep(String board, String hand) {
        if (board == null || board.length() == 0 || hand == null || hand.length() == 0) {
            return -1;
        }

        min = hand.length() + 1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : hand.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        helper(board, map, 0);

        return (min == hand.length() + 1) ? -1 : min;
    }

    private void helper(String board, HashMap<Character, Integer> map, int used) {
        int len = board.length();
        if (len == 0) {
            if (min > used) {
                min = used;
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            char c = board.charAt(i);
            Integer count = map.get(c);

            if (count == null) {
                continue;
            }

            if (i < len - 1 && c == board.charAt(i + 1)) {
                // add one ball
                int newCount = map.remove(c) - 1;
                if (newCount > 0) {
                    map.put(c, newCount);
                }
                String newBoard = removeBalls(board, i - 1, i + 2);

                helper(newBoard, map, used + 1);

                map.put(c, count);
            } else if (count >= 2) {
                // add two balls
                map.remove(c);
                if (count > 2) {
                    map.put(c, count - 2);
                }

                String newBoard = removeBalls(board, i - 1, i + 1);

                helper(newBoard, map, used + 2);

                map.put(c, count);
            }
        }
    }

    private String removeBalls(String board, int left, int right) {
        int len = board.length();

        while (left >= 0 && right < len) {
            char c = board.charAt(left);
            int count = 0;

            int l = left;
            while (l >= 0 && board.charAt(l) == c) {
                l--;
                count++;
            }

            int r = right;
            while (r < len && board.charAt(r) == c) {
                r++;
                count++;
            }

            if (count >= 3) {
                left = l;
                right = r;
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= left; i++) {
            sb.append(board.charAt(i));
        }

        for (int i = right; i < len; i++) {
            sb.append(board.charAt(i));
        }

        return sb.toString();
    }
}
/*
有一个testcase过不了
https://leetcode.com/problems/zuma-game/discuss/97007/Standard-test-program-is-wrong
Input     "RRWWRRBBRR"
          "WB"
Output    -1
Expected   2
RRWWRRBBRR -> RRWWRRBBR[W]R -> RRWWRRBB[B]RWR -> RRWWRRRWR -> RRWWWR -> RRR -> empty
 */



