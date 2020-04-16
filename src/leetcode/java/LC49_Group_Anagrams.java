package leetcode.java;

// LC49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/

import java.util.*;

// sort string, anagram sort后是一样的
// O(N*KlogK)
public class LC49_Group_Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}

// character counts一样，即是anagram
// O(NK)
/*
用 长度26的int[] 记录每个字母在这个单词里是否出现，1是出现，0是没有出现
用这个int[] 之后，变成String
比如： “abc”
int[] 结果： [1, 1, 1, 0, 0, 0, …..]
变成String：#1#1#1#0#0#0………. (用#间隔，因为可能某个字母出现次数超过个位数)
“bca”--> count[] → #1#1#1#0#0#0………….
计数完之后把array变成String
这样 看两个String是否相等 如果相等，就是一组 这样的比较就是O(1)

继续优化：用bit操作，用一个Integer来记录每个字母是否出现
 */
class LC49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        HashMap<String, List<String>> res  = new HashMap<>();
        int[] count = new int[26];

        for (String str: strs) {
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(str);
        }

        return new ArrayList<>(res.values());
    }
}