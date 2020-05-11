package leetcode.java;

// LC509. Fibonacci Number
// https://leetcode.com/problems/fibonacci-number/

public class LC509_Fibonacci_Number {
    public int fib(int N) {
        if(N <= 1)
            return N;

        int a = 0, b = 1;

        while (N-- > 1) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}

class LC509 {
    private Integer[] cache = new Integer[31];

    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        cache[0] = 0;
        cache[1] = 1;
        return memoize(N);
    }

    public int memoize(int N) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = memoize(N-1) + memoize(N-2);
        return memoize(N);
    }
}
