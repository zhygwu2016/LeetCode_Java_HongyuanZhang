package leetcode.java;

// 115. Distinct Subsequences
// https://leetcode.com/problems/distinct-subsequences/

// 算法加强
// int dp[i][j]: T [0, i) (短)
//               S [0, j) (长)  Total #
class LC115_DP {
    public int numDistinct(String s, String t) {
        if(s == null && t == null) return 0;

        int lenS = s.length(), lenT = t.length();
        int[][] dp = new int[lenT + 1][lenS + 1];
        for (int i = 0; i <= lenS; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < lenT; i++) {
            for (int j = i; j < lenS; j++) {
                char tc = t.charAt(i), sc = s.charAt(j);

                if (tc != sc) {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j]; // 不match & match
                }
            }
        }

        return dp[lenT][lenS];
    }
}

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
