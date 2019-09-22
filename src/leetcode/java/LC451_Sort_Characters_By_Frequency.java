package leetcode.java;

// 451. Sort Characters By Frequency
// https://leetcode.com/problems/sort-characters-by-frequency/

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC451_Sort_Characters_By_Frequency {
    public String frequencySort(String s){
        if(s == null || s.length() <= 2) return s;

        Map<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Sort with Heap
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                if(map.get(c1) == map.get(c2)){
                    return c1 - c2;
                }
                return map.get(c2) - map.get(c1);
            }
        });

        StringBuilder sb = new StringBuilder();

        for(Character c : map.keySet()){
            pq.offer(c);
        }

        while(!pq.isEmpty()){
            Character c = pq.poll();
            int count = map.get(c);

            while(count-- > 0){
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
