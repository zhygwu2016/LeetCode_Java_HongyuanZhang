package leetcode.java;

// 115. Distinct Subsequences
// https://leetcode.com/problems/distinct-subsequences/

public class LC115_Distinct_Subsequences {
    public int numDistinct(String s, String t) {
        if(s == null && t == null) return 0;

        int[][] result = new int[t.length() + 1][s.length() + 1];

        for(int i = 0; i < s.length() + 1; i++){
            result[0][i] = 1;
        }

        for(int i = 0; i < t.length(); i++){
            for(int j = 0; j < s.length(); j++){
                if(t.charAt(i) == s.charAt(j)){
                    result[i + 1][j + 1] = result[i + 1][j] + result[i][j];
                }else{
                    result[i + 1][j + 1] = result[i + 1][j];
                }
            }
        }

        return result[t.length()][s.length()];
    }
}
