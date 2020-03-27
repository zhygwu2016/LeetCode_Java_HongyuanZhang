package leetcode.java;

// LC269. Alien Dictionary
// https://leetcode.com/problems/alien-dictionary/

import java.util.*;

// https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
public class LC269_Alien_Dictionary {
    private final int N = 26;
    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        // visited: -1: not even existed
        //           0: unvisited
        //           1: visiting
        //           2: visited

        // 建图
        buildGraph(words, adj, visited);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {                 // unvisited
                if(!dfs(adj, visited, sb, i)) return "";
            }
        }
        return sb.reverse().toString();
    }

    public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
        visited[i] = 1;                            // 1 = visiting
        for (int j = 0; j < N; j++) {
            if (adj[i][j]) {                        // connected
                if(visited[j] == 1) return false;  // 1 => 1, cycle
                if(visited[j] == 0) {              // 0 = unvisited
                    if(!dfs(adj, visited, sb, j)) return false;
                }
            }
        }
        visited[i] = 2;                           // 2 = visited
        sb.append((char) (i + 'a'));
        return true;
    }

    public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        Arrays.fill(visited, -1);                 // -1 = not even existed
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                visited[c - 'a'] = 0;
            }

            // words[]中相邻两辆相比 第一个不同的字母分别为c1, c2
            // c1 --> c2
            if (i > 0) {
                String w1 = words[i - 1], w2 = words[i];
                int len = Math.min(w1.length(), w2.length());
                for (int j = 0; j < len; j++) {
                    char c1 = w1.charAt(j), c2 = w2.charAt(j);
                    if(c1 != c2) {
                        adj[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
}

// https://leetcode.com/problems/alien-dictionary/discuss/70119/Java-AC-solution-using-BFS
class LC269_Alien_Dictionary_BFS {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree = new HashMap<Character, Integer>();
        String result = "";

        if (words == null || words.length == 0) return result;

        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c,0);
            }
        }

        for (int i = 0; i < words.length - 1; i++){
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++){
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<Character>();
                    if (map.containsKey(c1)) set=map.get(c1);
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);
                    }
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<Character>();
        for (char c: degree.keySet()) {
            if (degree.get(c) == 0)  q.add(c);
        }
        while (!q.isEmpty()) {
            char c = q.poll();
            result += c;
            if (map.containsKey(c)) {
                for (char c2 : map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2)==0)  q.add(c2);
                }
            }
        }

        if (result.length() != degree.size()) return "";
        return result;
    }
}

// 算法加强
class LC269_Algorithm_Advanced {
    private int curLab = 0;

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0)  return "";
        if (words.length == 1)  return words[0];

        List<V> vs = new ArrayList<V>();
        V[] graph = new V[26];
        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            String cur = words[i];
            int idx1 = 0, idx2 = 0;
            boolean valid = false;

            while (idx1 < prev.length() && idx2 < cur.length()) {
                char c1 = prev.charAt(idx1++), c2 = cur.charAt(idx2++);
                if (graph[c1 - 'a'] == null) {
                    graph[c1 - 'a'] = new V(c1);
                    vs.add(graph[c1 - 'a']);
                }
                if (graph[c2 - 'a'] == null) {
                    graph[c2 - 'a'] = new V(c2);
                    vs.add(graph[c2 - 'a']);
                }
                if (c1 != c2) {
                    graph[c1 - 'a'].neighbours.add(graph[c2 - 'a']);
                    valid = true;
                    break;
                }
            }

            while (idx1 < prev.length()) {
                if (!valid)  return "";

                char c = prev.charAt(idx1++);
                if (graph[c - 'a'] == null) {
                    graph[c - 'a'] = new V(c);
                    vs.add(graph[c - 'a']);
                }
            }
            while (idx2 < cur.length()) {
                char c = prev.charAt(idx2++);
                if (graph[c - 'a'] == null) {
                    graph[c - 'a'] = new V(c);
                    vs.add(graph[c - 'a']);
                }
            }

            prev = cur;
        }

        char[] res = new char[vs.size()];
        curLab = res.length - 1;
        for (V v : vs) {
            if (!dfs(res, v, new HashSet<V>())) {
                return "";
            }
        }

        return new String(res);
    }

    private boolean dfs(char[] res, V cur, HashSet<V> cycle) {
        if (cycle.contains(cur))  return false;
        if (cur.visited)  return true;

        cur.visited = true;
        cycle.add(cur);
        for (V nei : cur.neighbours) {
            if (!dfs(res, nei, cycle)) {
                return false;
            }
        }
        cycle.remove(cur);

        res[curLab--] = cur.ch;
        return true;
    }

    private class V {
        public char ch;
        public boolean visited;
        public List<V> neighbours;

        public V(char ch) {
            this.ch = ch;
            this.visited = false;
            this.neighbours = new ArrayList<V>();
        }
    }
}
