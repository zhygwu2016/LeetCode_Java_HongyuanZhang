package leetcode.java;

// 772. Basic Calculator III
// https://leetcode.com/problems/basic-calculator-iii/

import java.util.Stack;

public class LC772_Basic_Calculator_III {
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
                if(ops.isEmpty() && nums.isEmpty()){
                    nums.push(0);
                    ops.push(c);
                    continue;
                }
                while(!ops.isEmpty() && hasPriority(c, ops.peek())){
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            } else if (c == '('){
                if(isOperand(s.charAt(i+1))){
                    nums.push(0);
                }
                ops.push(c);
            } else if (c == ')'){
                while(!ops.isEmpty() && ops.peek() != '('){
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.pop();
            }
        }

        while(!ops.isEmpty()){
            nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    private boolean isOperand(char c){
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private boolean hasPriority( char c1, char c2 ){
        if(c2 == '(' || c2 == ')') return false;
        return !( (c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-') );
    }

    private int operate(char c, int i1, int i2){
        switch(c){
            case '+' : return i2 + i1;
            case '-' : return i2 - i1;
            case '*' : return i2 * i1;
            case '/' : return i2 / i1;
        }
        return 0;
    }
}

/*
public class LC772_Basic_Calculator_III {
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
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            } else if (c == '('){
                ops.push(c);
            } else if (c == ')'){
                while(!ops.isEmpty() && ops.peek() != '('){
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.pop();
            }
        }

        while(!ops.isEmpty()){
            nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    private boolean isOperand( char c ){
        return ( c == '+' || c == '-' || c == '*' || c == '/');
    }

    private boolean hasPriority( char c1, char c2 ){
        if(c2 == '(' || c2 == ')') return false;
        return !( (c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-') );
    }

    private int operate(char c, int i1, int i2){
        switch(c){
            case '+' : return i2 + i1;
            case '-' : return i2 - i1;
            case '*' : return i2 * i1;
            case '/' : return i2 / i1;
        }
        return 0;
    }
}

 */
