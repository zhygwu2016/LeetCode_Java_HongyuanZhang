package leetcode.java;

// 44. Wildcard Matching
// https://leetcode.com/problems/wildcard-matching/

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
