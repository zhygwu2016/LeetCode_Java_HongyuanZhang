package leetcode.java;

// 69. Sqrt(x) (小数点后两位？ → lgN + lg100）
// https://leetcode.com/problems/sqrtx/

// 大弓
public class LC69_Sqrtx {
    public int mySqrt(int x) {
        if( x < 0 ) return -1;
        if( x < 1 ) return 0;
        if( x < 4 ) return 1;

        //int left = 1, right = Integer.MAX_VALUE;
        int left = 1, right = x;
        while(left + 1 < right){
            int mid = left + ( right - left)/2;
            // here the target becomes x/mid
            if(mid == x/mid) return mid;
            if(mid > x/mid){
                // Here, the mid is too large
                right = mid;
            }else{
                // Here, the mid is top small
                left = mid;
            }
        }

        if(right < (x/right) ) return right;
        return left;
    }
}

// 算法加强
class LC69 {
    public int mySqrt(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;

        int start = 1, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (x / mid >= mid && x / (mid + 1) < mid + 1) {
                // 为什么不用mid * mid <= x : mid*mid可能越界
                return mid;
            } else if (x / mid < mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // return 0;
        throw new RuntimeException("Should never ever happen!");
    }
}

