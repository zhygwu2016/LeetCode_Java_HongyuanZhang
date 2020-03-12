package leetcode.java;

// LC139. Word Break
// https://leetcode.com/problems/word-break/

import java.util.HashSet;
import java.util.List;

// Pruning
public class LC139_Word_Break {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        HashSet<String> set = new HashSet<>();
        int len = s.length();
        for (String word : wordDict) {
            set.add(word);
        }

        Boolean[] mem = new Boolean[len + 1];
        return dfs(s, 0, set, mem);
    }

    private boolean dfs(String s, int idx, HashSet<String> dict, Boolean[] mem) {
        int len = s.length();
        if (idx == len) {
            return true;
        }
        if (mem[idx] != null) {
            return mem[idx];
        }

        for (int i = idx; i < len; i++) {
            String str = s.substring(idx, i + 1);
            if (dict.contains(str)) {
                if (dfs(s, i + 1, dict, mem)) {
                    mem[idx] = true;
                    return true;
                }
            }
        }

        mem[idx] = false;
        return mem[idx];
    }
}

// DP
class LC139_Word_Break_DP {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        HashSet<String> set = new HashSet<>();
        int len = s.length();
        for (String word : wordDict) {
            set.add(word);
        }

        // dp[i]: [i, len - 1]的substring是true还是false
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                String str = s.substring(i, j);
                if (set.contains(str) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
    }
}
