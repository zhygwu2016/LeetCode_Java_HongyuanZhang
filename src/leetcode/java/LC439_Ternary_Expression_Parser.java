package leetcode.java;

// LC439. Ternary Expression Parser
// https://leetcode.com/problems/ternary-expression-parser/

import java.util.Stack;

public class LC439_Ternary_Expression_Parser {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }

        Stack<String> stack = new Stack<String>();
        int len = expression.length(), i = len - 1;

        while (i >= 0) {
            char c = expression.charAt(i);

            if (c == '?') {
                String val1 = stack.pop();
                String val2 = stack.pop();

                char flag = expression.charAt(--i);
                if (flag == 'T') {
                    stack.push(val1);
                } else {
                    stack.push(val2);
                }

                i--;
            } else if (c == ':') {
                i--;
            } else {
                StringBuilder sb = new StringBuilder();
                while (i >= 0
                        && expression.charAt(i) != '?'
                        && expression.charAt(i) != ':') {
                    sb.append(expression.charAt(i--));
                }
                stack.push(sb.reverse().toString());
            }
        }

        return stack.pop();
    }
}
