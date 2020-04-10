package leetcode.java;

// LC287. Find the Duplicate Number
// https://leetcode.com/problems/find-the-duplicate-number/

// https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.
// 龟兔赛跑
// 算法类似LinkedList查环找环的起点
public class LC287_Find_the_Duplicate_Number {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast)
        {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (fast != slow)
        {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
