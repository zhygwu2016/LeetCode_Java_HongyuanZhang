package leetcode.java;

// LC334. Increasing Triplet Subsequence
// https://leetcode.com/problems/increasing-triplet-subsequence/

// 和LC300Longest Increasing Subsequence的Greedy方法类似：
// 此题keep一个size为3的buffer即可
// 优化：只需要记录两个变量： first和second
public class LC334_Increasing_Triplet_Subsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;

        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

        for (int third : nums) {
            if (third > second) {
                return true;
            } else if (third < first) {
                first = third;
            } else if (third > first && third < second) {
                second = third;
            }
        }

        return false;
    }
}
