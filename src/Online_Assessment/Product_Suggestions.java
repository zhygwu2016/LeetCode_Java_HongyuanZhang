package Online_Assessment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Product_Suggestions {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<List<String> > threeProductSuggestions(
            int numProducts, List<String> repository, String customerQuery){
        // WRITE YOUR CODE HERE
        List<List<String>> res = new LinkedList<>();

        // corner case
        if (numProducts == 0 || repository == null || repository.size() == 0 ||
                customerQuery == null || customerQuery.length() <= 1) {
            return res;
        }
        TrieNode root = buildTrie(repository);
        customerQuery = customerQuery.toLowerCase();

        for (int i = 2; i <= customerQuery.length(); i++) {
            String prefix = customerQuery.substring(0, i);
            TrieNode cur = startsWith(prefix, root);
            if (cur == null) {
                return res;
            }
            List<String> words = new ArrayList<String>();
            StringBuilder sb = new StringBuilder();
            findThreeWords(words, sb.append(prefix.substring(0, prefix.length() - 1)), cur);
            res.add(words);
        }

        return res;

    }

    class TrieNode {
        char val;
        TreeMap<Character, TrieNode> children;
        boolean isWord;

        private TrieNode(char c){
            this.val = c;
            children = new TreeMap<>();
            isWord = false;
        }
    }

    public void findThreeWords(List<String> words, StringBuilder sb, TrieNode root){
        if (words.size() == 3)  return;
        sb.append(root.val);
        int len = sb.length();
        if (root.isWord)  words.add(sb.toString());
        for (TrieNode node : root.children.values()) {
            if (node != null)  findThreeWords(words, sb, node);
            sb.setLength(len);
        }
    }

    public TrieNode buildTrie(List<String> rep){
        TrieNode root = new TrieNode('0');
        for (String s : rep) {
            s = s.toLowerCase();
            TrieNode cur = root;
            for (int i = 0; i < s.length(); i++) {
                if(cur.children.get(s.charAt(i)) == null) {
                    cur.children.put(s.charAt(i), new TrieNode(s.charAt(i)));
                }
                cur = cur.children.get(s.charAt(i));
            }
            cur.isWord = true;
        }
        return root;
    }

    public TrieNode startsWith(String prefix, TrieNode root){
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.children.containsKey(prefix.charAt(i)))  return null;
            node = node.children.get(prefix.charAt(i));
        }
        return node;
    }
    // METHOD SIGNATURE ENDS
}

/*
1. Define Trie Node, save top 3 words in each Trie Node.

2. Build the Trie by using repository.

3. Search customerQuery which is a string on the Trie we built,
   add top 3 words for each nodes we go through on Trie during the search process.

4. Return the result.

Time:
(1) Build Trie: length * number of repositories
     length is the max length of a repository
(2) Search query on Trie: O(length of Query)

Space: Trie length -> length of longest repository
 */