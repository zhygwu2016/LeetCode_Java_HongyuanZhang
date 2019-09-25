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
