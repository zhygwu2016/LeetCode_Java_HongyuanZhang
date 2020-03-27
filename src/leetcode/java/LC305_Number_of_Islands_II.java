package leetcode.java;

// 305. Number of Islands II
// https://leetcode.com/problems/number-of-islands-ii/

import java.util.ArrayList;
import java.util.List;

public class LC305_Number_of_Islands_II {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if(positions == null) return res;

        int row = m, col = n;
        UnionFind uf = new UnionFind(row, col);
        for(int[] point : positions){
            int i = point[0], j = point[1], p = uf.index(point[0], point[1]);
            uf.addIsland(p);

            List<int[]> neis = getNeighbour(i, j, row, col);
            for(int[] nei : neis){
                int q = uf.index(nei[0], nei[1]);
                if(uf.isIsland(q) && !uf.find(p, q)) uf.union(p, q);
            }
            res.add(uf.numOfIslands);
        }
        return res;
    }

    private List<int[]> getNeighbour(int i, int j, int row, int col){
        List<int[]> res = new ArrayList<int[]>();
        if(i - 1 >= 0){ int[] one = new int[2]; one[0] = i - 1; one[1] = j; res.add(one); }
        if(i + 1 < row){ int[] one = new int[2]; one[0] = i + 1; one[1] = j; res.add(one); }
        if(j - 1 >= 0){ int[] one = new int[2]; one[0] = i; one[1] = j - 1; res.add(one); }
        if(j + 1 < col){ int[] one = new int[2]; one[0] = i; one[1] = j + 1; res.add(one); }

        return res;
    }
}

class UnionFind{
    public int numOfIslands, row, col;
    public int[] parents, size;

    public UnionFind(int row, int col){
        this.numOfIslands = 0;
        this.row = row;
        this.col = col;
        int len = row * col;
        parents = new int[len];
        size = new int[len];
        for(int i = 0; i < len; i++){
            parents[i] = -1;
            size[i] = 1;
        }
    }

    public boolean find(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int rootP = root(p), rootQ = root(q);
        if(size[rootP] < size[rootQ]) union(q, p);
        else {
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
            this.numOfIslands--;
        }
    }

    private int root(int p){
        int tempP = p;
        while(parents[p] != p){
            parents[p] = parents[parents[p]];
            p = parents[p];
        }
        parents[tempP] = p;
        return p;
    }

    public int index(int i, int j){
        return i * col + j;
    }

    public void addIsland(int p){
        if(parents[p] == -1){
            parents[p] = p;
            numOfIslands++;
        }
    }

    public boolean isIsland(int p){
        return parents[p] != -1;
    }
}


