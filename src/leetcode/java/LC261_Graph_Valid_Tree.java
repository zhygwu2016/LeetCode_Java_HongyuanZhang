package leetcode.java;

// LC261. Graph Valid Tree
// https://leetcode.com/problems/graph-valid-tree/

public class LC261_Graph_Valid_Tree {

    class UnionFind{
        public int size;
        public int[] ids, sz;

        public UnionFind(int n) {
            size = n;
            ids = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
                sz[i] = 1;
            }
        }

        private int getRoot(int i) {
            int cur = i;
            while (ids[cur] != cur) {
                ids[cur] = ids[ids[cur]];
                cur = ids[cur];
            }
            ids[i] = cur;
            return cur;
        }

        public boolean find(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public void union(int p, int q) {
            int pRoot = getRoot(p), qRoot = getRoot(q);
            if (sz[pRoot] < sz[qRoot]) {
                int temp = pRoot;
                pRoot = qRoot;
                qRoot = temp;
            }
            ids[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
            this.size--;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges == null) return false;
        if (n != edges.length + 1) return false;

        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            int p = e[0], q = e[1];
            if (uf.find(p, q)) {
                return false; // 如果p,q本来就已连接，还要union，就会成环，就不可能是tree
            } else {
                uf.union(p, q);
            }
        }

        return uf.size == 1;
    }
}
// Time: O(NlogN)
