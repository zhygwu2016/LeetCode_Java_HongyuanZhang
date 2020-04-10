package leetcode.java;

// LC507. Perfect Number
// https://leetcode.com/problems/perfect-number/

public class LC507_Perfect_Number {
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
            if(sum>num) {
                return false;
            }
        }
        return sum == num;
    }
}

// 根号N优化
// 我们只需要check 1~根号N的范围
// 如果num/i可以整除，我们就加上i和num/i
// 因为有1，所以1被加进去，num/1=num也被加进去了。所以最后sum-num就是我们求的和
class LC507 {
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }
}

class LC507_simple {
    public boolean checkPerfectNumber(int num) {
        int[] array = new int[]{6, 28, 496, 8128, 33550336};

        for (int arr : array) {
            if (num == arr) return true;
        }

        return false;
    }
}

