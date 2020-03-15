package Online_Assessment.Microsoft;

// https://leetcode.com/discuss/interview-question/365872/

import java.util.HashMap;
import java.util.Map;

public class Numbers_With_Equal_Digit_Sum {
    private int computeDigitSum(int a){
        // supposed to be valid for negative numbers and the output must be non-negative integer.
        a = Math.abs(a);
        int res = 0;
        while(a > 0){
            res += a % 10;
            a /= 10;
        }
        return res;
    }
    public int maxSum(int[] A){
        int N = A.length;
        if(N <= 1) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        int res = -1;
        for(int i = 0; i < N; ++i){
            int digitsum = computeDigitSum(A[i]);
            if(!map.containsKey(digitsum)){
                map.put(digitsum, A[i]);
            }
            else{
                res = Math.max(res, map.get(digitsum) + A[i]);
                map.put(digitsum, Math.max(A[i], map.get(digitsum)));
            }
        }
        return res;
    }
}

// Java O(NlogK) time complexity & O(N) space complexity solution with playground.
// There contains logK since computing the digit sum of A[i], which complexity is log(A[i]) with base 10.
