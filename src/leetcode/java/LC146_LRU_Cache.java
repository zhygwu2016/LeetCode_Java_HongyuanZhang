package leetcode.java;

// LC146. LRU Cache
// https://leetcode.com/problems/lru-cache/

import java.util.HashMap;
import java.util.Map;

// Hashmap + DoubleLinkedList
public class LC146_LRU_Cache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private void addNode(DLinkedNode node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node){
        /**
         * Remove an existing node from the linked list.
         */
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkedNode node){
        /**
         * Move certain node in between to the head.
         */
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        /**
         * Pop the current tail.
         */
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LC146_LRU_Cache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        // head.prev = null;

        tail = new DLinkedNode();
        // tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if(node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            size++;

            if(size > capacity) {
                // pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// 另一种写法
class Node { // public variables for simplicity
    int key;
    int value;
    Node next;
    Node prev;

    public Node(int k, int v) {
        key = k;
        value = v;
        next = null;
        prev = null;
    }
}

class DoublyLinkedList {
    private Node head = new Node(0, 0); // dummy node
    private Node tail = new Node(0, 0); // dummy node

    public DoublyLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node n) {
        if (n == null) {
            return;
        }
        n.prev = head;
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
    }

    public void remove(Node n) { // Assumes 'n' is in this list
        if (n == null || n.prev == null || n.next == null) {
            return;
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    public Node getFirst() {
        if (head.next == tail) {
            return null; // list has 0 Nodes
        }
        return head.next;
    }

    public Node getLast() {
        if (head.next == tail) {
            return null; // list has 0 Nodes
        }
        return tail.prev;
    }
}
class LRUCache {
    private int capacity;
    private Map<Integer, Node> map; // gives us O(1)-time access to Nodes
    private DoublyLinkedList dll;   // used to keep track of "freshness" of Nodes

    public LRUCache(int capacity) {
        this.capacity = (capacity < 1) ? 1 : capacity;
        map = new HashMap<>();
        dll = new DoublyLinkedList();
    }

    public void put(int key, int value) {
        remove(key); // If key already exists, we will overwrite it.
        if (map.size() >= capacity) {
            remove(dll.getLast().key);
        }
        Node n = new Node(key, value);
        dll.addFirst(n);
        map.put(key, n);
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1; // Problem wants -1 returned. It's better to throw Exception instead.
        }
        if (n != dll.getFirst()) {
            updateFreshness(n);
        }
        return n.value;
    }

    public void remove(int key) {
        Node n = map.get(key);
        dll.remove(n);
        map.remove(key);
    }

    private void updateFreshness(Node n) { // Assumes 'n' is in this list
        dll.remove(n);
        dll.addFirst(n);
    }
}

