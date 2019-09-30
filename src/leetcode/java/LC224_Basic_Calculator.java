package leetcode.java;

// 224. Basic Calculator
// https://leetcode.com/problems/basic-calculator/

import java.util.Stack;

public class LC224_Basic_Calculator {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;

        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int sign = 1;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                int num = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                result += num * sign;
            }else if(c == '+'){
                sign = 1;
            }else if(c == '-'){
                sign = -1;
            }else if(c == '('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if(c == ')'){
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }
}
