package leetcode.java;

// 438. Find All Anagrams in a String
// https://leetcode.com/problems/find-all-anagrams-in-a-string/

import java.util.ArrayList;
import java.util.List;

public class LC438_Find_All_Anagrams_in_a_String {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || p == null || p.length() > s.length()){
            return result;
        }

        for(int i = 0; i <= s.length() - p.length(); i++){
            if(checkPattern(s.substring(i, i + p.length()), p)){
                result.add(i);
            }
        }
        return result;
    }

    private boolean checkPattern(String s1, String s2){
        if(s1.length() != s2.length()) return false;

        int[] pattern = new int[26];

        for(char s : s1.toCharArray()){
            pattern[s - 'a']++;
        }

        for(char s : s2.toCharArray()){
            pattern[s - 'a']--;
        }

        for(int i : pattern){
            if(i != 0) return false;
        }

        return true;
    }
}
