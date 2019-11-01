package leetcode.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 140. Word Break II
// https://leetcode.com/problems/word-break-ii/

public class LC140_Word_Break_II {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        if(s == null || s.length() == 0) return result;
        boolean[] mem = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>();

        for(int i = 0; i < mem.length; i++){
            mem[i] = true;
        }
        for(String str : wordDict){
            set.add(str);
        }

        dfs(s, set, result, new StringBuilder(), 0, mem);

        return result;
    }

    private void dfs(String s, Set<String> set, List<String> result, StringBuilder sb, int index, boolean[] mem){
        if(index == s.length()){
            sb.setLength(sb.length() - 1);
            result.add(sb.toString());
            return;
        }

        int size = result.size();

        for(int i = index + 1; i <= s.length(); i++){
            String word = s.substring(index, i);

            if(set.contains(word) && mem[i]){
                int len = sb.length();

                sb.append(word);
                sb.append(" ");
                dfs(s, set, result, sb, i, mem);
                sb.setLength(len);
            }
        }

        if(result.size() == size){
            mem[index] = false;
        }
    }
}
