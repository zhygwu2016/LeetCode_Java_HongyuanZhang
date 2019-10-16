package leetcode.java;

// 190. Reverse Bits
// https://leetcode.com/problems/reverse-bits/

public class LC190_Reverse_Bits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // corner case all 0 or all 1   n == 0 || n == -1
        if(n == 0 || n == -1) return n;

        int result = 0;
        int mask = 0;

        for(int i = 0; i < 32; i++){
            mask = (n >> i) & 1;
            result |= (mask << 31 - i);
        }
        return result;
    }
}

class LC190_Reverse_Bits_Solution_2 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if(n == 0 || n == -1) return n;
        for(int i = 0; i < 16; i++){
            int left = 1 & (n >> (31 - i));
            int right = 1 & (n >> i);
            if(left != right){
                n ^= 1 << (31 - i);
                n ^= 1 << i;
            }
        }
        return n;
    }
}
