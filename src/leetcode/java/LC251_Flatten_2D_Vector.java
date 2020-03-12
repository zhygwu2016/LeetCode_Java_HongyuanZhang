package leetcode.java;

// LC251. Flatten 2D Vector
// https://leetcode.com/problems/flatten-2d-vector/

public class LC251_Flatten_2D_Vector {
}

class Vector2D {
    int[][] v;
    int i = 0;
    int j = 0;

    public Vector2D(int[][] v) {
        this.v = v;
    }

    public int next() {
        int val = v[i][j];
        j = j + 1 < v[i].length ? j + 1 : 0;
        i = j == 0 ? i + 1 : i;
        return val;
    }

    public boolean hasNext() {
        while (i < v.length && (v[i] == null || v[i].length == 0)) {
            i++;
        }
        return i < v.length && j < v[i].length;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
