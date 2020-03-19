package leetcode.java;

// LC132. Palindrome Partitioning II
// https://leetcode.com/problems/palindrome-partitioning-ii/

public class LC132_Palindrome_Partitioning_II {
    public int minCut(String s) {
        int len = s.length();

        // isPalindrome[i][j]: 是否Palindrome
        boolean[][] m = new boolean[len][len];

        // dp[i]: [i, len) → min # (Palindrome)
        int[] rec = new int[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            rec[i] = len - i;
            for (int j = i; j < len; j++) {
                if (i == j || (s.charAt(i) == s.charAt(j) && (j == i + 1 || m[i + 1][j - 1]))) {
                    rec[i] = Math.min(rec[i], rec[j + 1] + 1);
                    m[i][j] = true;
                }
            }
        }

        return rec[0] - 1;
    }
}
