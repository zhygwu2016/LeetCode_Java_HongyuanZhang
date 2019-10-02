package leetcode.java;

// 150. Evaluate Reverse Polish Notation
// https://leetcode.com/problems/evaluate-reverse-polish-notation/

import java.util.Stack;

public class LC150_Evaluate_Reverse_Polish_Notation {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0) return 0;

        Stack<Integer> stack = new Stack<>();

        for(String str : tokens){
            if(isNumber(str)){
                stack.push(Integer.parseInt(str));
            } else if(isOperand(str)){
                stack.push(operate(str, stack.pop(), stack.pop()));
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String str){
        if(str == null || str.length() == 0)  return false;
        char c = str.charAt(0);

        if(!Character.isDigit(c)){
            if(!(c == '+' || c == '-')) return false;
            else if(str.length() == 1) return false;
        }

        for(int i = 1; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))) return false;
        }

        return true;
    }

    private boolean isOperand(String str){
        if(str == null || str.length() != 1) return false;
        char c = str.charAt(0);
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private int operate(String str, int i1, int i2){
        char c = str.charAt(0);
        switch(c){
            case '+': return i2 + i1;
            case '-': return i2 - i1;
            case '*': return i2 * i1;
            case '/': return i2 / i1;
        }
        return 0;
    }
}
