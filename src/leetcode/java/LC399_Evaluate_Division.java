package leetcode.java;

// LC399. Evaluate Division
// https://leetcode.com/problems/evaluate-division/

import java.util.HashMap;
import java.util.List;

/*
equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return       [6.0,       0.5,      -1.0,       1.0,      -1.0 ].
 */

// 算法加强
// 分析详见算法加强笔记
public class LC399_Evaluate_Division {
    private class V{
        public String name;
        public double val;
        public V parent;
        private int size;
        // 引入val: a与b若有共同的root,为了建立a与b通过root建立起来的联系：
        // val = parent / this
        // a --> x    a.val = x / a
        // b --> x    b.val = x / b
        // 则 a / b = (x/a.val) / (x/b.val) = b.val / a.val

        public V(String name) {
            this.name = name;
            this.parent = this;
            this.val = 1.0; // 初始，自己的parent就是自己，val为1
            this.size = 1;
        }
    }

    private boolean find(V v1, V v2) {
        return root(v1) == root(v2);
    }

    private V root(V v) {
        V cur = v;
        double d = 1.0;
        while (cur != cur.parent) {
            cur.val *= cur.parent.val;
            d *= cur.val;

            cur.parent = cur.parent.parent;
            cur = cur.parent;
        }

        // 最后，v的parent是root
        // 所以需要记录一个d, 最后v.val = d, 是在最后一步 v与root之间建立的联系
        v.parent = cur;
        v.val = d;
        return cur;
    }

    private void union(V v1, V v2, double d) { // d = v1 / v2
        V root1 = root(v1), root2 = root(v2);
        if (root1.size > root2.size) { // root1 <-- root2
            root2.parent = root1;
            root2.val = v1.val * d / v2.val;
            root1.size += root2.size;
        } else {                       // root1 --> root1
            root1.parent = root2;
            root1.val = v2.val / (v1.val * d);
            root2.size += root1.size;
        }
    }

    private double division(V v1, V v2) {
        return v2.val / v1.val;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {
        int len = equations.size(), lenQ = queries.size();
        double[] res = new double[lenQ];
        HashMap<String, V> map = new HashMap<String, V>();

        for (int i = 0; i < len; i++) {
            List<String> equa = equations.get(i);
            String s1 = equa.get(0), s2 = equa.get(1);
            double d = values[i];

            if (!map.containsKey(s1))  map.put(s1, new V(s1));
            if (!map.containsKey(s2))  map.put(s2, new V(s2));

            V v1 = map.get(s1);
            V v2 = map.get(s2);
            if (!find(v1, v2)) { // 如果没连，那就连上~
                union(v1, v2, d);
            }
        }

        for (int i = 0; i < lenQ; i++) {
            List<String> query = queries.get(i);
            String s1 = query.get(0), s2 = query.get(1);

            if (!map.containsKey(s1) || !map.containsKey(s2)) {
                res[i] = -1.0;
            } else {
                V v1 = map.get(s1), v2 = map.get(s2);
                if (find(v1, v2)) {
                    res[i] = division(v1, v2);
                } else {
                    res[i] = -1.0;
                }
            }
        }

        return res;
    }
}
