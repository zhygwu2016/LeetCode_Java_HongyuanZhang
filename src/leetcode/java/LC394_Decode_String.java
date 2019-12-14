package leetcode.java;

// LC394. Decode String
// https://leetcode.com/problems/decode-string/

import java.util.Stack;

public class LC394_Decode_String {
    public String decodeString(String s){
        if(s == null || s.length() == 0) return "";

        int len = s.length();
        Stack<StringBuilder> stack = new Stack<StringBuilder>();
        Stack<Integer> countStack = new Stack<Integer>();
        stack.push(new StringBuilder());

        int curVal = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                curVal = curVal * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(new StringBuilder());
                countStack.push(curVal);
                curVal = 0;
            } else if (c == ']') {
                int count = countStack.pop();
                StringBuilder str = stack.pop();

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    sb.append(str);
                }

                stack.peek().append(sb);
            } else {
                // Characters
                stack.peek().append(c);
            }
        }

        return stack.pop().toString();
    }
}
