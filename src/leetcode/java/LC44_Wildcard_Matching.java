package leetcode.java;

// 44. Wildcard Matching
// https://leetcode.com/problems/wildcard-matching/
/*
Given an input string (s) and a pattern (p),
implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
 */

// 算法加强
class LC44 {
    public boolean isMatch(String s, String p) {
        if(s == null && p == null) return true;
        if(s == null || p == null) return false;

        int lenS = s.length(), lenP = p.length();
        int i = 0, j = 0;
        int flag = -1, tempS = 0;

        // 全局只有一个flag
        // 遇见下一个 * 时，flag会被覆盖掉
        // while loop只具备track一个 * 的能力

        while (i < lenS) {
            if (j < lenP && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            } else if (j < lenP && p.charAt(j) == '*') {
                // 遇见*，flag是*的下一位
                // 此时，*匹配的是s中的null
                flag = ++j;
                tempS = i;
            } else if (flag != -1) {
                // * 匹配的是s中的1个字符，2两个字符，3个字符...
                // 直到遇见其他情况
                j = flag;
                i = ++tempS;
            } else {
                return false;
            }
        }

        while (j < lenP && p.charAt(j) == '*') {
            j++;
        }

        return j == lenP;
    }
}
// Time: O(MN)
// s: a a a a a a a b
// p: * a a a b

public class LC44_Wildcard_Matching {
    public boolean isMatch(String s, String p) {
        if(s == null && p == null) return true;
        if(s == null || p == null) return false;

        return isMatch(s, p, 0, 0) == 2;
    }

    private int isMatch(String s, String p, int i, int j){
        if(i == s.length() && j == p.length()) return 2;
        if(i == s.length() && p.charAt(j) != '*') return 0;
        if(j == p.length()) return 1;

        if(p.charAt(j) == '*'){
            if(j + 1 < p.length() && p.charAt(j + 1) == '*'){
                return isMatch(s, p, i, j + 1);
            }

            for(int k = 0; k <= s.length() - i; k++){
                int result = isMatch(s, p, i + k, j + 1);
                if(result == 0 || result == 2) return result;
            }
        }

        if(p.charAt(j) == '?' || (p.charAt(j) == s.charAt(i))){
            return isMatch(s, p, i + 1, j + 1);
        }

        return 1;
    }
}
