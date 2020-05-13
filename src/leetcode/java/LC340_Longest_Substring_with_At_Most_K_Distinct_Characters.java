package leetcode.java;

// LC340. Longest Substring with At Most K Distinct Characters
// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

import java.util.Collections;
import java.util.HashMap;

// counter
public class LC340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k <= 0)  return 0;

        int len = s.length(), num = 0, start = 0, ret = 0;
        int[] count = new int[256];

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (count[c]++ == 0) {
                num++;
            }

            while (num > k) {
                if (--count[s.charAt(start++)] == 0) {
                    num--;
                }
            }

            ret = Math.max(ret, i - start + 1);
        }

        return ret;
    }
}

// HashMap
class LC340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n*k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 1;

        while (right < n) {
            // add new character and move right pointer
            hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}

// 复合数据结构 HashMap + DoubleLinkedList
class LC340_MyQueue {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k == 0)  return 0;

        int len = s.length(), max = 0;

        MyQueue queue = new MyQueue(k);

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            queue.add(c, i);

            max = Math.max(max, queue.getSize(i));
        }

        return max;
    }

    class MyQueue {
        public Node head, tail;
        public HashMap<Character, Node> map;
        public int start, size, k;

        public MyQueue(int k) {
            this.k = k;
            map = new HashMap<Character, Node>();
            head = new Node('\0', 0);
            tail = new Node('\0', 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
            start = 0;
        }

        public int getSize(int index) {
            return index - this.start + 1;
        }

        public void add(char ch, int idx) {
            Node node = new Node(ch, idx);
            if (map.containsKey(ch)) {
                Node n = map.get(ch);
                remove(n);
            } else if (map.size() == k) {
                start = removeHeadNode() + 1;
            }

            map.put(ch, node);
            addToTail(node);
        }

        private int removeHeadNode() {
            map.remove(head.next.ch);
            int ret = head.next.index;
            Node next = head.next.next;
            head.next = next;
            next.prev = head;
            return ret;
        }

        private void addToTail(Node n) {
            Node prev = tail.prev;
            prev.next = n;
            n.prev = prev;
            n.next = tail;
            tail.prev = n;
        }

        private void remove(Node n) {
            Node prev = n.prev;
            Node next = n.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    class Node {
        public int index;
        public char ch;
        public Node prev, next;

        public Node(char ch, int index) {
            this.index = index;
            this.ch = ch;
            this.prev = prev;
            this.next = next;
        }
    }
}


