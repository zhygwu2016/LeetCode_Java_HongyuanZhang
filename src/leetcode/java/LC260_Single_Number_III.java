package leetcode.java;

// 260. Single Number III
// https://leetcode.com/problems/single-number-iii/

public class LC260_Single_Number_III {
    /*
        Let a and b be the two unique numbers
        XORing all numbers gets you (a xor b)
        (a xor b) must be non-zero otherwise they are equal
        If bit_i in (a xor b) is 1, bit_i at a and b are different.
        Find bit_i using the highestOneBit
        Partition the numbers into two groups: one group with bit_i == 1
        and the other group with bit_i == 0.
        a is in one group and b is in the other.
        a is the only single number in its group.
        b is also the only single number in its group.
        XORing all numbers in a's group to get a
        XORing all numbers in b's group to get b
        Alternatively, XOR(a xor b) with a gets you b.
     */
    public int[] singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];

        int diff = 0;

        // After this loop, diff = a XOR b
        for(int n : nums){
            diff ^= n;
        }

        // Find one bit that can distinguish a and b
        diff = Integer.highestOneBit(diff);

        int[] result = new int[2];

        for(int n : nums){
            if((diff & n) == 0){
                // XORing all numbers in a's group to get a
                result[0] ^= n;
            } else {
                // XORing all numbers in b's group to get b
                result[1] ^= n;
            }
        }

        return result;
    }
}
