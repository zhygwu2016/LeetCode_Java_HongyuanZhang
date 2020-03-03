package leetcode.java;

// LC320. Generalized Abbreviation
// https://leetcode.com/problems/generalized-abbreviation/

import java.util.ArrayList;
import java.util.List;

public class LC320_Generalized_Abbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        if (word == null) return res;

        dfs(res, new StringBuilder(), 0, 0, word);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, int prevCnt, int index, String word) {
        int len = word.length();
        if (index == len) {
            if (prevCnt > 0) {
                int lenP = path.length();
                path.append(prevCnt);
                res.add(path.toString());
                path.setLength(lenP);
            } else {
                res.add(path.toString());
            }
            return;
        }

        // regard this char as a number, and count 1
        dfs(res, path, prevCnt + 1, index + 1, word);

        // keep the original char
        int lenP = path.length();
        if (prevCnt > 0) {
            path.append(prevCnt);
        }
        path.append(word.charAt(index));
        dfs(res, path, 0, index + 1, word);
        path.setLength(lenP);
    }
}
