package leetcode.java;

// LC241. Different Ways to Add Parentheses
// https://leetcode.com/problems/different-ways-to-add-parentheses/

import java.util.ArrayList;
import java.util.List;

public class LC241_Different_Ways_to_Add_Parentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        boolean singleNum = true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                singleNum = false;
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));

                List<Integer> one = combine(left, right, c);
                res.addAll(one);
            }
        }

        if (singleNum == true) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

    private List<Integer> combine(List<Integer> left, List<Integer> right, char optr) {
        List<Integer> res = new ArrayList<Integer>();
        for (int l : left) {
            for (int r : right) {
                int result = 0;
                if (optr == '+')  result = l + r;
                if (optr == '-')  result = l - r;
                if (optr == '*')  result = l * r;
                res.add(result);
            }
        }
        return res;
    }
}
