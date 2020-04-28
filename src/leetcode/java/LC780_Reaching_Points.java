package leetcode.java;

// LC780. Reaching Points
// https://leetcode.com/problems/reaching-points/

// https://leetcode.com/problems/reaching-points/discuss/114856/JavaC%2B%2BPython-Modulo-from-the-End
public class LC780_Reaching_Points {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) ty %= tx;
            else tx %= ty;
        }

        return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
                sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}
