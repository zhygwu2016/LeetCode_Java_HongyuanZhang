package leetcode.java;

import java.util.HashSet;
import java.util.Set;

// 算法加强
// boolean dp[i][j]: s1 [0, i)
//                   s2 [0, j)    与s3 [0, k)  match T/F
class LC97_DP {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1 == null && s2 == null && s3 == null) return true;
        if(s1 == null || s2 == null || s3 == null) return false;

        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3)  return false;

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 0; i < len1; i++) {
            dp[i + 1][0] = dp[i][0] && (s1.charAt(i) == s3.charAt(i));
        }
        for (int i = 0; i < len2; i++) {
            dp[0][i + 1] = dp[0][i] && (s2.charAt(i) == s3.charAt(i));
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int k = i + j + 1;
                if (s1.charAt(i) == s3.charAt(k)) {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
                if (s2.charAt(j) == s3.charAt(k)) {
                    dp[i + 1][j + 1] = dp[i + 1][j + 1] || dp[i + 1][j];
                }
            }
        }

        return dp[len1][len2];
    }
}

public class LC97_Interleaving_String {
/*
    The private method isInterleave is the recursive method. It takes additional
    i1, i2, i3 as the start indices of s1, s2, s3, so it solves the substring of
    s1, s2, s3 with those start indices.

    The recursive starting condition si i1, i2, i3 are set to 0, means it solves
    the whole string.

    In each recursion, it will just check the first character in s3 with s2 and s1,
    if it equals to s1, it will increase i3 and i1 to solve remain, if remain return
    true, this recursion will also return true. Same logic for s2.

    The end condition si when remain of either s1 or s2 is empty, then just compare
    remain of s3 with remain of s1 or s2, if they are equal, it will return true.

    A pure recursive solution will cause time limit exceed. We can optimize it by
    caching the false visited solutions in the visited set. That will short circuit
    many repeated search path.
 */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1 == null && s2 == null && s3 == null) return true;
        if(s1 == null || s2 == null || s3 == null) return false;

        if(s1.length() + s2.length() != s3.length()) return false;

        Set<Integer> visited = new HashSet<>();

        return isInterleave(s1, 0, s2, 0, s3, 0, visited);
    }

    private boolean isInterleave(String s1, int i1, String s2, int i2, String s3, int i3, Set<Integer> visited){
        int hash = i1 + i2 * s3.length();

        if(visited.contains(hash)) return false;

        if(i1 == s1.length()) return s2.substring(i2).equals(s3.substring(i3));
        if(i2 == s2.length()) return s1.substring(i1).equals(s3.substring(i3));

        if(s1.charAt(i1) == s3.charAt(i3) && isInterleave(s1, i1 + 1, s2, i2, s3, i3 + 1, visited) ||
           s2.charAt(i2) == s3.charAt(i3) && isInterleave(s1, i1, s2, i2 + 1, s3, i3 + 1, visited)){
            return true;
        }

        visited.add(hash);
        return false;
    }
}
