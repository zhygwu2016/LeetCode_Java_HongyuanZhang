package leetcode.java;

// 438. Find All Anagrams in a String
// https://leetcode.com/problems/find-all-anagrams-in-a-string/

import java.util.*;

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

// https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
class LC438_Find_All_Anagrams_in_a_String_2 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if(s == null || s.length() == 0 || p == null || p.length() > s.length()){
            return result;
        }

        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();

        int begin = 0, end = 0;

        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) count--;
            }
            end++;

            while(count == 0){
                char temp = s.charAt(begin);
                if(map.containsKey(temp)){
                    map.put(temp, map.get(temp) + 1);
                    if(map.get(temp) > 0){
                        count++;
                    }
                }
                if(end - begin == p.length()){
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
    }
}
