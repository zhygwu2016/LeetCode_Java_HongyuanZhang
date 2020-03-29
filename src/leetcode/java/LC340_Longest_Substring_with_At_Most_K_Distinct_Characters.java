package leetcode.java;

// LC340. Longest Substring with At Most K Distinct Characters
// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

// counter
public class LC340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k <= 0)  return 0;

        int len = s.length(), num = 0, start = 0, ret = 0;
        int[] count = new int[256];

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (count[c]++ == 0) {
                num++;
            }

            while (num > k) {
                if (--count[s.charAt(start++)] == 0) {
                    num--;
                }
            }

            ret = Math.max(ret, i - start + 1);
        }

        return ret;
    }
}
