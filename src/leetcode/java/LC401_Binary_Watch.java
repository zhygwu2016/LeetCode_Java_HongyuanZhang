package leetcode.java;

// LC401. Binary Watch
// https://leetcode.com/problems/binary-watch/

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-watch/discuss/88458/Simple-Python%2BJava
public class LC401_Binary_Watch {
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num) {
                    // because the biggest number of minutes is 59,
                    // which is "111011", 6 bit, so need to left move h to 6 bit
                    // which is h<<6 or h*64
                    times.add(String.format("%d:%02d", h, m));
                    // %d means an integer
                    // %02d means an integer, left padded with zeros up to 2 digits.
                }
            }
        }
        return times;
    }
}

class LC401 {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount((h << 6) | m) == num) {
                    res.add(h + ":" + ((m < 10) ? ("0" + m) : m));
                }
            }
        }
        return res;
    }
}
