package leetcode.java;

import java.util.*;

public class LC692_Top_K_Frequent_Words {
    public List<String> topKFrequent(String[] words, int k){
        List<String> result = new LinkedList<>();

        if(words == null || words.length == 0 || k <= 0) return result;

        Map<String, Integer> map = new HashMap<>();

        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(map.get(s1) == map.get(s2)){
                    return s2.compareTo(s1);
                }
                return map.get(s1) - map.get(s2);
            }
        });
        // 频率：从少到多排列
        // 字数： 从大到小排列
        // 取最后k个，每次插到result的开头

        for(String str : map.keySet()){
            pq.offer(str);

            if(pq.size() > k){
                pq.poll();
            }
        }

        while(!pq.isEmpty()){
            // result.addFirst(pq.poll());
            result.add(0, pq.poll());
        }

        return (List)result;
    }
}
