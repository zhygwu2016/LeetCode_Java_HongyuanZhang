package leetcode.java;

// 231. Power of Two
// https://leetcode.com/problems/power-of-two/

public class LC231_Power_of_Two {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        if(n == 1) return true;
        if(n % 2 != 0) return false;

        return isPowerOfTwo(n / 2);
    }
}

// x & (-x) is a way to keep the rightmost 1-bit
// and to set all the other bits to 0.
class LC231_Solution_2 {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (-x)) == x;
    }
}

// x & (x - 1) is a way to set the rightmost 1-bit to zero
class LC231_Solution_3 {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (x - 1)) == 0;
    }
}
