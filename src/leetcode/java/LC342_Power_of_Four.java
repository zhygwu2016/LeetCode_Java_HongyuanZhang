package leetcode.java;

// 342. Power of Four
// https://leetcode.com/problems/power-of-four/

public class LC342_Power_of_Four {
    public boolean isPowerOfFour(int num) {
        if(num <= 0) return false;
        if(num == 1) return true;
        if(num % 4 != 0) return false;

        return isPowerOfFour(num / 4);
    }
}
