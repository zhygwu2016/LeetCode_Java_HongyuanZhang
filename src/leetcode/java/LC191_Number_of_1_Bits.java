package leetcode.java;

// 191. Number of 1 Bits
// https://leetcode.com/problems/number-of-1-bits/

public class LC191_Number_of_1_Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if(n == 0) return 0;

        int count = 0, mask = 1;

        for(int i = 0; i < 32; i++){
            if((n & mask) != 0) count++;
            mask <<= 1;
        }
        return count;
    }
}
