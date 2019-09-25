package leetcode.java;

// 70. Climbing Stairs
// https://leetcode.com/problems/climbing-stairs/

public class LC70_Climbing_Stairs {
    public int climbStairs(int n){
        if(n <= 2) return n;

        int prePre = 1;
        int pre = 1;
        int cur = 2;

        for(int i = 2; i <= n; i++){
            cur = prePre + pre;
            prePre = pre;
            pre = cur;
        }

        return cur;
    }
}

// 2. dp
class LC70_dp{
    public int LC70_dp(int n){
        if(n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
