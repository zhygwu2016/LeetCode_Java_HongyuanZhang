package leetcode.java;

// LC32. Longest Valid Parentheses
//https://leetcode.com/problems/longest-valid-parentheses/
// Given a string containing just the characters '(' and ')',
// find the length of the longest valid (well-formed) parentheses substring.

// !!! substring !!! substring !!! substring!!!

// 前后扫两遍
public class LC32_Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        int longest = 0;
        int extra=0;
        int length=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                extra++;
                length++;
            } else {
                if (extra > 0) {
                    extra--;
                    length++;
                    if (extra == 0) {
                        longest = Math.max(longest, length);
                    }
                } else {
                    extra = 0;
                    length = 0;
                }
            }
        }

        length = 0;
        extra=0;

        for(int i = s.length()-1; i >= 0; i--) {
            if(s.charAt(i) == ')') {
                extra++;
                length++;
            } else {
                if (extra > 0) {
                    extra--;
                    length++;
                    if (extra == 0) {
                        longest = Math.max(longest, length);
                    }
                } else {
                    extra = 0;
                    length=0;
                }
            }
        }
        return longest;
    }
}
