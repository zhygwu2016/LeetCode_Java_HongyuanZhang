package leetcode.java;
// LC211. Add and Search Word - Data structure design
// https://leetcode.com/problems/add-and-search-word-data-structure-design/

public class LC211_Add_and_Search_Word_Data_structure_design {
}

// 算法加强
class TrieNode {
    public char ch;
    public TrieNode[] children;
    public boolean isLeaf = false;

    public TrieNode(char c){
        ch = c;
        children = new TrieNode[26];
    }
}

class WordDictionary {

    private TrieNode root = new TrieNode('\0');

    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode cur, String word, int index) {
        int len = word.length();
        if (cur == null) return false;
        if (index == len) return cur.isLeaf;

        char ch = word.charAt(index);
        if (ch != '.') {
            return dfs(cur.children[ch - 'a'], word, index + 1);
        } else {
            for (TrieNode next : cur.children) {
                if (dfs(next, word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */


//public class LC211_Add_and_Search_Word_Data_structure_design {
//    /** Initialize your data structure here. */
////    public WordDictionary() {
////
////    }
//
//    public class TrieNode {
//        public TrieNode[] children = new TrieNode[26];
//        public boolean isWord;
//    }
//
//    private TrieNode root = new TrieNode();
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        TrieNode node = root;
//        for (char c : word.toCharArray()) {
//            if (node.children[c - 'a'] == null) {
//                node.children[c - 'a'] = new TrieNode();
//            }
//            node = node.children[c - 'a'];
//        }
//        node.isWord = true;
//    }
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//        return match(word.toCharArray(), 0, root);
//    }
//
//    private boolean match(char[] chs, int k, TrieNode node) {
//        if (k == chs.length) {
//            return node.isWord;
//        }
//        if (chs[k] == '.') {
//            for (int i = 0; i < node.children.length; i++) {
//                if (node.children[i] != null && match(chs, k + 1, node.children[i])) {
//                    return true;
//                }
//            }
//        } else {
//            return node.children[chs[k] - 'a'] != null
//                    && match(chs, k + 1, node.children[chs[k] - 'a']);
//        }
//        return false;
//    }
//}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

// Time Complexity:
// addWord() - O(n), n = length of the new word
// search() - Worst case: O(m), m = the total number of characters in the Trie