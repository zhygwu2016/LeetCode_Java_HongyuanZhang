package leetcode.java;

// LC159. Longest Substring with At Most Two Distinct Characters
// https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

import java.util.Collections;
import java.util.HashMap;

// slide window
public class LC159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            // slidewindow contains less than 3 characters
            if (hashmap.size() < 3)
                hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == 3) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}

// 算法加强
class LC159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0)  return 0;

        int len = s.length();

        // 分别记录两个distinct character的value以及对应的index
        char c1 = '\0', c2 = '\0';
        int idx1 = -1, idx2 = -1;

        int start = 0;
        int max = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == c1) {
                idx1 = i;
            } else if (c == c2) {
                idx2 = i;
            } else { // 出现了第三个distinct character
                if (idx1 < idx2) {
                    start = idx1 + 1;
                    c1 = c;
                    idx1 = i;
                } else {
                    start = idx2 + 1;
                    c2 = c;
                    idx2 = i;
                }
            }

            max = Math.max(max, i - start + 1);
        }

        return max;
    }
}

