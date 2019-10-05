package leetcode.java;

// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/

import java.util.*;

public class LC347_Top_K_Frequent_Elements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new LinkedList<>();
        if(nums == null || nums.length == 0 || k <= 0) return result;

        Map<Integer, Integer> map = new HashMap<>();

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if(map.get(i1).equals(map.get(i2))) return 0;
                return map.get(i1) > map.get(i2) ? 1 : -1;
            }
        });

        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for(int key : map.keySet()){
            if(heap.size() < k){
                heap.offer(key);
            }else{
                int freq = map.get(key);
                if(freq > map.get(heap.peek())){
                    heap.offer(key);
                    heap.poll();
                }
            }
        }

        while(!heap.isEmpty()){
            result.add(0, heap.poll());
        }

        return result;
    }
}
