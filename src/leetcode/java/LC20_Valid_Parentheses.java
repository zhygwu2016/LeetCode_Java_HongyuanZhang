package leetcode.java;

import java.util.Stack;

public class LC20_Valid_Parentheses {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return true;

        Stack<Character> stack =  new Stack<>();

        for(char c : s.toCharArray()){
            if(isLeft(c)){
                stack.push(c);
            } else if(isRight(c)){
                if(stack.isEmpty()) return false;

                if(!isMatch(stack.pop(), c)) return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char c){
        return (c == '(' || c == '[' || c == '{');
    }

    private boolean isRight(char c){
        return (c == ')' || c == ']' || c == '}');
    }

    private boolean isMatch(char c1, char c2){
        return (c1 == '(' && c2 == ')') ||
                (c1 == '[' && c2 == ']') ||
                (c1 == '{' && c2 == '}');
    }
}


class LC20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
