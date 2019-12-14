package leetcode.java;

// 772. Basic Calculator III
// https://leetcode.com/problems/basic-calculator-iii/

import java.util.HashMap;
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


// 算法哥
class Calculator_III_AlgorithmMamba{
    private static final HashMap<Character, Integer> charWeightMap =
            new HashMap<Character, Integer>(){{
                put('+', 1);
                put('-', 1);
                put('*', 2);
                put('/', 2);
            }};

    public int calculate(String s){
        if(s == null){
            throw new IllegalArgumentException();
        }

        int len = s.length();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> opters = new Stack<>();

        addOpter('(', nums, opters, s, -1);

        int i = 0;
        while(i < len){
            char ch = s.charAt(i);
            if (charWeightMap.containsKey(ch) || ch == '(' || ch == ')'){
                addOpter(ch, nums, opters, s, i);
                i++;
            } else if (ch >= '0' && ch <= '9') {
                int num = 0;
                while(i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                nums.push(num);
            } else if ( ch == ' '){
                i++;
            } else {
                throw new IllegalArgumentException();
            }
        }

        addOpter(')', nums, opters, s, len);

        return nums.peek();
    }

    private void addOpter(char optr, Stack<Integer> nums, Stack<Character> opters, String s, int i){
        if (optr == '(') {
            opters.push(optr);
            int idx = i + 1;
            while(idx < s.length()){
                char ch = s.charAt(idx);
                if (ch == ' '){
                    idx++;
                } else {
                    if (ch == '-'){
                        nums.push(0);
                    }

                    break;
                }
            }
        } else if (optr == ')'){
            while(!opters.isEmpty()){
                char cur = opters.pop();
                if (cur == '(') {
                    break;
                }

                int num2 = nums.pop();
                int num1 = nums.pop();
                nums.push(cal(cur, num1, num2));
            }
        } else if (charWeightMap.containsKey(optr)) {
            while (true) {
                if (opters.isEmpty()) {
                    break;
                }
                char top = opters.peek();
                Integer wei = charWeightMap.get(top);
                if (wei == null || wei < charWeightMap.get(optr)) {
                    break;
                }

                int num2 = nums.pop();
                int num1 = nums.pop();
                nums.push(cal(opters.pop(), num1, num2));
            }
            opters.push(optr);
        } else {
            throw new IllegalStateException();
        }
    }

    private int cal(char optr, int num1, int num2) {
        switch (optr) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num1 / num2;
            default: throw new IllegalStateException();
        }
    }
}
