package leetcode.java;

// 227. Basic Calculator II
// https://leetcode.com/problems/basic-calculator-ii/

import java.util.Stack;

public class LC227_Basic_Calculator_II {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;

        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') continue;

            if(Character.isDigit(c)){
                int num = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (isOperand(c)) {
                while(!ops.isEmpty() && hasPriority(c, ops.peek())){
                    nums.push(operator(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            }
        }

        while(!ops.isEmpty()){
            nums.push(operator(ops.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    private boolean isOperand( char c ){
        return ( c == '+' || c == '-' || c == '*' || c == '/');
    }

    private boolean hasPriority( char c1, char c2 ){
        return !( (c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-') );
    }

    private int operator(char c, int i1, int i2){
        switch(c){
            case '+' : return i2 + i1;
            case '-' : return i2 - i1;
            case '*' : return i2 * i1;
            case '/' : return i2 / i1;
        }
        return 0;
    }
}
