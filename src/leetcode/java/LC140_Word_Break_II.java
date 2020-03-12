package leetcode.java;

import java.util.*;

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

// 算法加强
class Solution_LC140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        boolean[] m = new boolean[len + 1];
        Arrays.fill(m, true);
        HashSet<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }

        search(res, new StringBuilder(), s, 0, dict, m);
        return res;
    }

    private void search(List<String> res, StringBuilder path,
                        String s, int idx, Set<String> dict, boolean[] m) {
        int len = s.length();
        if (idx == len) {
            res.add(path.toString());
            return;
        }

        int curSize = res.size();
        for (int i = idx + 1; i <= s.length(); i++) {
            String str = s.substring(idx, i);
            if (dict.contains(str) && m[i]) {
                int lenPath = path.length();
                if (lenPath == 0) {
                    path.append(str);
                } else {
                    path.append(" " + str);
                }

                search(res, path, s, i, dict, m);
                path.setLength(lenPath);
            }
        }

        // 如果res长度没变，说明没办法继续走，pruning
        if (curSize == res.size()) {
            m[idx] = false;
        }
    }
}
