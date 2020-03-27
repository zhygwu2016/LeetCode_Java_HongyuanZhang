package somethingElse.java;

// Union Find
// Find O(logN)
// Union O(logN)
public class Union_Find {
    class V {
        V parent;
        int size;
    }

    class UnionFind{
        private V getRoot(V v) {
            V cur = v;
            while (cur.parent != null) {
                cur.parent = cur.parent.parent;
                cur = cur.parent;
            }
            v.parent = cur;
            return cur;
        }

        public boolean find(V p, V q) {
            return getRoot(p) == getRoot(q);
        }

        public void Union(V p, V q) {
            V pRoot = getRoot(p);
            V qRoot = getRoot(q);
            if (pRoot.size < qRoot.size) { // p -> q
                pRoot.parent = qRoot;
                qRoot.size += pRoot.size;
            } else { // q -> p
                qRoot.parent = pRoot;
                pRoot.size += qRoot.size;
            }
        }
    }
}
