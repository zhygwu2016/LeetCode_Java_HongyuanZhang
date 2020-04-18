package leetcode.java;

// LC763. Partition Labels
// https://leetcode.com/problems/partition-labels/

import java.util.ArrayList;
import java.util.List;

public class LC763_Partition_Labels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;

        int[] lastAppearance  = new int[26];

        for (int i = 0; i < S.length(); i++) {
            lastAppearance[S.charAt(i) - 'a'] = i;
        }

        int left = 0, right = 0;
        for (int i = 0; i < S.length(); i++) {
            right = Math.max(right, lastAppearance[S.charAt(i) - 'a']);
            if (right == i) {
                res.add(i - left + 1);
                left = i + 1;
            }
        }

        return res;
    }
}
