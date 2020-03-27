package leetcode.java;

// LC721. Accounts Merge
// https://leetcode.com/problems/accounts-merge/

import java.util.*;

// 算法加强
// Union Find
public class LC721_Accounts_Merge {
    private static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int capacity) {
            this.parent = new int[capacity];
            this.size = new int[capacity];

            for (int i = 0; i < capacity; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        public boolean find(int user1, int user2) {
            return getRoot(user1) == getRoot(user2);
        }

        public void union(int user1, int user2) {
            int root1 = getRoot(user1);
            int root2 = getRoot(user2);

            if (size[root1] < size[root2]) { // root1 --> root2
                parent[root1] = root2;
                size[root2] += size[root1];
            } else { // root1 <-- root2
                parent[root2] = root1;
                size[root1] += size[root2];
            }
        }

        public int getRoot(int user) {
            int cur = user;

            while (parent[cur] != cur) {
                parent[cur] = parent[parent[cur]];
                cur = parent[cur];
            }

            parent[user] = cur;
            return cur;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) {
            throw new IllegalArgumentException();
        }

        int len = accounts.size();
        UnionFind uf = new UnionFind(len);

        // Map: email --> user
        // 如果【用户i】的email在【用户user】中出现过，说明是同一个人，union(user, i)
        HashMap<String, Integer> emailToUserMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                Integer user = emailToUserMap.get(email);
                if (user == null) {
                    emailToUserMap.put(email, i);
                } else {
                    uf.union(user, i);
                }
            }
        }

        // Map: user --> email们
        // 建立此map, 用于输出结果
        HashMap<Integer, HashSet<String>> userToEmailMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = uf.getRoot(i);
            if (!userToEmailMap.containsKey(root)) {
                userToEmailMap.put(root, new HashSet<>());
            }

            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                userToEmailMap.get(root).add(account.get(j));
            }
        }

        // 输出结果
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, HashSet<String>> e : userToEmailMap.entrySet()) {
            List<String> list = new LinkedList<>();

            list.addAll(e.getValue());
            Collections.sort(list);

            list.add(0, accounts.get(e.getKey()).get(0));

            res.add(list);
        }

        return res;
    }
}
