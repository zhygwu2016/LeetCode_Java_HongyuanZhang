package leetcode.java;

// LC981. Time Based Key-Value Store
// https://leetcode.com/problems/time-based-key-value-store/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class LC981_Time_Based_Key_Value_Store {
}

// HashMap + Binary Search
// 假设set每次的timestamp是递增的？
class TimeMap {

    class Node {
        String value;
        int timestamp;

        public Node(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    /** Initialize your data structure here. */
    HashMap<String, List<Node>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<Node>());
        }
        map.get(key).add(new Node(value, timestamp));
    }

    public String get(String key, int timestamp) {
        List<Node> nodes = map.get(key);
        if (nodes == null) return "";

        int left = 0, right = nodes.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            Node node = nodes.get(mid);
            if (node.timestamp == timestamp) {
                return node.value;
            } else if (node.timestamp < timestamp) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nodes.get(right).timestamp <= timestamp) return nodes.get(right).value;
        if (nodes.get(left).timestamp <= timestamp) return nodes.get(left).value;
        return "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

// HashMap + TreeMap
class TimeMap_TreeMap {
    /** Initialize your data structure here. */

    // TreeMap key: timestamp; value: 题中的value
    // 这样就能按照timestamp的排序进行set和get
    HashMap<String, TreeMap<Integer, String>> map;

    public TimeMap_TreeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null)  return "";

        Integer floorKey = treeMap.floorKey(timestamp);

        if (floorKey == null) return "";
        return treeMap.get(floorKey);
    }
}
