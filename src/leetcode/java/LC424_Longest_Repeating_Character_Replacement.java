package leetcode.java;

// LC424. Longest Repeating Character Replacement
// https://leetcode.com/problems/longest-repeating-character-replacement/

// sliding window
// #(window) - #(most frequent character) <= k  ==>  valid
public class LC424_Longest_Repeating_Character_Replacement {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) return 0;

        int[] counter = new int[26];
        int len = s.length(), start = 0, max = 0;
        // most frequent character
        char maxChar = '\0';

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            counter[c - 'A']++;
            if (maxChar == '\0' || counter[c - 'A'] > counter[maxChar - 'A']) {
                maxChar = c;
            }

            // when curLen - counter[maxChar] > k
            while (i - start + 1 - counter[maxChar - 'A'] > k) {
                char startChar = s.charAt(start++);
                counter[startChar - 'A']--;

                if (startChar == maxChar) { // update maxChar
                    for (char ch = 'A'; ch <= 'Z'; ch++) {
                        if (counter[ch - 'A'] > counter[maxChar - 'A']) {
                            maxChar = ch;
                        }
                    }
                }
            }

            max = Math.max(max, i - start + 1);
        }

        return max;
    }
}
