package leetcode.java;

// 50. Pow(x, n)
// https://leetcode.com/problems/powx-n/

public class LC50_Pow_x_n {
    public double myPow(double x, int n){
        if(n == 0) return 1;
        if(x == 1) return 1;
        if(x == -1) return n % 2 == 0 ? 1 : -1;
        if(n == 1) return x;

        if(n == Integer.MIN_VALUE) return Math.abs(x) > 1 ? 0 : Integer.MAX_VALUE;

        if(n < 0) return 1.0 / myPow(x, -n);

        double temp = myPow(x, n/2);
        return n % 2 == 0 ? temp * temp : temp * temp * x;
    }
}
