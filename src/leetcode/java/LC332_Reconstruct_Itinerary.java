package leetcode.java;

// LC332. Reconstruct Itinerary
// https://leetcode.com/problems/reconstruct-itinerary/

/*
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// Euler circuit
public class LC332_Reconstruct_Itinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<String>();
        if (tickets == null || tickets.size() == 0 || tickets.get(0) == null) {
            return res;
        }

        HashMap<String, PriorityQueue<String>> graph = new HashMap<String, PriorityQueue<String>>();

        for (List<String> pair : tickets) {
            String from = pair.get(0), to = pair.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<String>());
            }
            graph.get(from).offer(to);
        }

        search(res, "JFK", graph);

        return res;
    }

    private void search(List<String> res, String cur, HashMap<String, PriorityQueue<String>> graph) {
        PriorityQueue<String> nexts = graph.get(cur);
        while (nexts != null && !nexts.isEmpty()) {
            String to = nexts.poll();
            search(res, to, graph);
        }

        res.add(0, cur);
    }
}
