package leetcode.java;

// LC205. Isomorphic Strings
// https://leetcode.com/problems/isomorphic-strings/

// The idea is that we need to map a char to another one,
// for example, "egg" and "add", we need to construct the mapping
// 'e' -> 'a' and 'g' -> 'd'.
// Instead of directly mapping 'e' to 'a',
// another way is to mark them with same value,
// for example, 'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2, this works same.
public class LC205_Isomorphic_Strings {
    public boolean isIsomorphic(String s, String t) {
        if ((s == null && t == null) || (s.length() == 0 && t.length() == 0)) return true;
        if (s == null || t == null || s.length() == 0 || t.length() == 0
                || s.length() != t.length()) {
            return false;
        }

        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
            // i + 1 是因为m1 m2初始值是0，0代表还没构建联系
        }
        return true;
    }
}
