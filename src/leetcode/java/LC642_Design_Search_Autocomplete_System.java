package leetcode.java;

// LC642. Design Search Autocomplete System
// https://leetcode.com/problems/design-search-autocomplete-system/

import java.util.*;
import java.util.stream.Collectors;

public class LC642_Design_Search_Autocomplete_System {
}

// 算法加强
class AutocompleteSystem {

    private TrieNode root;
    private TrieNode curNode;
    private StringBuilder path;
    private HashMap<String, Integer> countBook; // global HashMap 存所有句子以及对应count

    public AutocompleteSystem(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            throw new IllegalArgumentException();
        }
        root = new TrieNode('\0');
        curNode = root;
        path = new StringBuilder();
        countBook = new HashMap<>();
        int len = sentences.length;
        for (int i = 0; i < len; i++) {
            countBook.put(sentences[i], times[i]);
            insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') { // 如果一个句子输入完毕
            curNode = root; // 回到起点，以备下一次的autocomplete

            String insertMe = path.toString();
            Integer count = countBook.get(insertMe);
            if (count == null) {
                countBook.put(insertMe, 1);
            } else {
                countBook.put(insertMe, count + 1);
            }
            insert(insertMe, countBook.get(insertMe)); // 将输入后的变化更新到Trie中

            path = new StringBuilder();
            return new ArrayList<>(); // 输入结束，return an empty list
        }

        path.append(c);

        // 如果上一次的input就没有autocomplete
        // (上一轮curNode == null; 且返回空list)
        // 那么这一次输入，也会返回空list
        if (curNode == null) {
            return new ArrayList<>();
        }

        int index = (c >= 'a' && c <= 'z') ? c - 'a' : 26;
        curNode = curNode.nexts[index];
        if (curNode == null) {
            return new ArrayList<>();
        }

        // return输入到这个char时，top 3 sentences
        return getTop3String(curNode.countMap);
    }

    // 更新Trie
    private void insert(String sentence, int times) {
        TrieNode cur = root;
        for (char ch : sentence.toCharArray()) {
            int index = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode(ch);
            }
            TrieNode next = cur.nexts[index];

            next.countMap.put(sentence, times);
            List<Pair> top3 = getTop3Pair(next.countMap);
            next.countMap.clear();
            // countMap里put新的sentence-times; 清空countMap; put进新的top3 key-value
            for (Pair p : top3) {
                next.countMap.put(p.str, p.count);
            }

            cur = next;
        }

        cur.isLeaf = true;
    }

    // 将top3 pair转成top3 String(sentences)
    private List<String> getTop3String(HashMap<String, Integer> countMap) {
        return getTop3Pair(countMap)
                .stream()
                .map(p -> p.str)
                .collect(Collectors.toList());
        // https://www.geeksforgeeks.org/stream-in-java/
    }

    private List<Pair> getTop3Pair(HashMap<String, Integer> countMap) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (a, b) ->
                        (a.count == b.count
                                ? a.str.compareTo(b.str)
                                : b.count - a.count));

        for (Map.Entry<String, Integer> e : countMap.entrySet()) {
            maxHeap.offer(new Pair(e.getKey(), e.getValue()));
        }

        List<Pair> ret = new ArrayList<>();
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) { // maxHeap里可能少于3个
            ret.add(maxHeap.poll());
        }

        return ret;
    }

    class TrieNode {
        public char ch;
        public TrieNode[] nexts;
        public HashMap<String, Integer> countMap;
        public boolean isLeaf;

        public TrieNode(char ch) {
            this.ch = ch;
            nexts = new TrieNode[27]; // a - z 和 space
            this.isLeaf = false;
            this.countMap = new HashMap<>(3);
        }
    }

    public class Pair {
        public String str;
        public int count;

        public Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
