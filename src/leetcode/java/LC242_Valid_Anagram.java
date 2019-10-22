package leetcode.java;

// 242. Valid Anagram
// https://leetcode.com/problems/valid-anagram/

public class LC242_Valid_Anagram {
    public boolean isAnagram(String s, String t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.length() != t.length()) return false;

        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i++){
            int pos = s.charAt(i) - 'a';
            freq[pos] += 1;
        }

        for(int j = 0; j < t.length(); j++){
            int pos = t.charAt(j) - 'a';
            freq[pos] -= 1;
        }

        for(int i : freq){
            if(i != 0) return false;
        }

        return true;
    }
}
