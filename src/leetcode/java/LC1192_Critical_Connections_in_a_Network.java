package leetcode.java;

// LC1192. Critical Connections in a Network
// https://leetcode.com/problems/critical-connections-in-a-network/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1192_Critical_Connections_in_a_Network {
    private int t = 1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();

        // build graph
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> conn : connections) {
            graph.get(conn.get(0)).add(conn.get(1));
            graph.get(conn.get(1)).add(conn.get(0));
        }

        // an array to save the timestamp that we meet a certain node
        int[] ts = new int[n];

        List<List<Integer>> res = new ArrayList<>();

        dfs(res, 0, -1, graph, ts);

        return res;
    }

    // return the minimum timestamp started from cur in the graph
    // prev --> cur, determine this edge
    private int dfs(List<List<Integer>> res, int cur, int prev,
                    List<List<Integer>> graph, int[] ts) {
        if (ts[cur] > 0) {
            return ts[cur];
        }

        ts[cur] = t++;

        int minTimestamp = Integer.MAX_VALUE;

        for (int next : graph.get(cur)) {
            if (next == prev) { // ignore the edge where 'cur' comes from
                continue;
            }

            int neighborTimestamp = dfs(res, next, cur, graph, ts);

            minTimestamp = Math.min(minTimestamp, neighborTimestamp);
        }

        // determine whether (prev, cur) is a critical edge
        if (minTimestamp >= ts[cur]) {
            if (prev >= 0) { // 排除起始 prev = -1
                res.add(Arrays.asList(prev, cur));
            }
        }

        return Math.min(ts[cur], minTimestamp);
    }
}
