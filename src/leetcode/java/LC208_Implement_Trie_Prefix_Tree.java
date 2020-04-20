package leetcode.java;

// 208. Implement Trie (Prefix Tree)
// https://leetcode.com/problems/implement-trie-prefix-tree/

public class LC208_Implement_Trie_Prefix_Tree {
}

// 算法加强
//class TrieNode {
//    public char c;
//    public boolean isLeaf = false;
//    public TrieNode[] children = new TrieNode[26];
//
//    public TrieNode(){
//
//    }
//
//    public TrieNode(char c) {
//        this.c = c;
//    }
//}

class Trie {
    class TrieNode {
        public char c;
        public boolean isLeaf = false;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode(){

        }

        public TrieNode(char c) {
            this.c = c;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(word.charAt(i));
            }
            cur = cur.children[idx];
        }
        cur.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return false;
            } else {
                cur = cur.children[idx];
            }
        }
        return cur.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return false;
            } else {
                cur = cur.children[idx];
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


// https://leetcode.com/articles/implement-trie-prefix-tree/
//class TrieNode{
//
//    // R links to node children
//    private TrieNode[] links;
//
//    private final int R = 26;
//
//    private boolean isEnd;
//
//    public TrieNode(){
//        links = new TrieNode[R];
//    }
//
//    public boolean containsKey(char ch){
//        return links[ch - 'a'] != null;
//    }
//    public TrieNode get(char ch){
//        return links[ch - 'a'];
//    }
//    public void put(char ch, TrieNode node){
//        links[ch - 'a'] = node;
//    }
//    public void setEnd(){
//        isEnd = true;
//    }
//    public boolean isEnd(){
//        return isEnd;
//    }
//}
//
//class Trie {
//    private TrieNode root;
//
//    /** Initialize your data structure here. */
//    public Trie() {
//        root = new TrieNode();
//    }
//
//    /** Inserts a word into the trie. */
//    public void insert(String word) {
//        TrieNode node = root;
//        for(int i = 0; i < word.length(); i++){
//            char currentChar = word.charAt(i);
//            if(!node.containsKey(currentChar)){
//                node.put(currentChar, new TrieNode());
//            }
//            node = node.get(currentChar);
//        }
//        node.setEnd();
//    }
//
//    // search a prefix or whole key in trie and
//    // returns the node where search ends
//    private TrieNode searchPrefix(String word){
//        TrieNode node = root;
//        for(int i = 0; i < word.length(); i++){
//            char curLetter = word.charAt(i);
//            if(node.containsKey(curLetter)){
//                node = node.get(curLetter);
//            }else{
//                return null;
//            }
//        }
//        return node;
//    }
//
//    /** Returns if the word is in the trie. */
//    public boolean search(String word) {
//        TrieNode node = searchPrefix(word);
//        return node != null && node.isEnd();
//    }
//
//    /** Returns if there is any word in the trie that starts with the given prefix. */
//    public boolean startsWith(String prefix) {
//        TrieNode node = searchPrefix(prefix);
//        return node != null;
//    }
//}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */