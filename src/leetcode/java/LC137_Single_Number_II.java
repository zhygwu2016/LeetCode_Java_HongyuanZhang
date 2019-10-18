package leetcode.java;

// 137. Single Number II
// https://leetcode.com/problems/single-number-ii/

public class LC137_Single_Number_II {
    /*
    The usual bit manipulation code is bit hard to get and replicate.
    I'd like to think about the number in 32 bits and just count how many 1s are there
    in each bit, and sum %= 3 will clear it once it reaches 3.
    After running for all the numbers for each bit, if we have a 1, that 1 belongs to
    the single number, we can simply move it back to its spot by doing ans |= sum << i;

    This has complexity of O(32n), which is essentially O(n)
    and very easy to think and implement. Plus, you get a general solution for any times
    of occurrence. Say all the numbers have 5 times, just do sum %= 5.

     */
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return Integer.MAX_VALUE;

        int answer = 0;

        for(int i = 0; i < 32; i++){
            int digitSum = 0;
            for(int j = 0; j < nums.length; j++){
                if(((nums[j] >> i) & 1) == 1){
                    digitSum++;
                    // filter out the digits that belong to the num that appears 3 times
                    digitSum %= 3;
                }
            }

            // Assign the digit back to the number which appears only one time
            if(digitSum != 0){
                answer |= digitSum << i;
            }
        }
        return answer;
    }
}
