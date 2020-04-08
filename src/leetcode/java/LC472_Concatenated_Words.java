package leetcode.java;

// LC472. Concatenated Words
// https://leetcode.com/problems/concatenated-words/

import java.util.*;

// https://leetcode.com/problems/concatenated-words/discuss/95652/Java-DP-Solution
// Word Break 升级版
// words按长度从小到大排序，看每个词(word)能不能由它前面的词(dictionary)构成
public class LC472_Concatenated_Words {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }

        return result;
    }

    private boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        // dp[i]: [i, len) 是否canForm
        dp[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                String str = word.substring(i, j);
                if (dict.contains(str) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}

// Trie + DFS
class LC472 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0) {
            return res;
        }
        TrieNode root = new TrieNode();
        for (String word : words) { // construct Trie tree
            if (word.length() == 0) {
                continue;
            }
            addWord(word, root);
        }
        for (String word : words) { // test word is a concatenated word or not
            if (word.length() == 0) {
                continue;
            }
            if (testWord(word.toCharArray(), 0, root, 0)) {
                res.add(word);
            }
        }
        return res;
    }
    public boolean testWord(char[] chars, int index, TrieNode root, int count) { // count means how many words during the search path
        TrieNode cur = root;
        int n = chars.length;
        for (int i = index; i < n; i++) {
            if (cur.children[chars[i] - 'a'] == null) {
                return false;
            }
            if (cur.children[chars[i] - 'a'].isEnd) {
                if (i == n - 1) { // no next word, so test count to get result.
                    return count >= 1;
                }
                if (testWord(chars, i + 1, root, count + 1)) {
                    return true;
                }
            }
            cur = cur.children[chars[i] - 'a'];
        }
        return false;
    }
    public void addWord(String str, TrieNode root) {
        char[] chars = str.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isEnd = true;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }
}

