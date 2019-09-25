package leetcode.java;

// 326. Power of Three
// https://leetcode.com/problems/power-of-three/

public class LC326_Power_of_Three {
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        if(n == 1) return true;
        if(n % 3 != 0) return false;

        return isPowerOfThree(n / 3);
    }
}
