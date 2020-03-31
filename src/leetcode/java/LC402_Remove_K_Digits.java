package leetcode.java;

// LC402. Remove K Digits
// https://leetcode.com/problems/remove-k-digits/

import java.util.Stack;

public class LC402_Remove_K_Digits {
    public String removeKdigits(String num, int k) {
        if (k >= num.length())  return "0";

        Stack<Character> stack = new Stack<>();

        for (char c : num.toCharArray()) {
            while (stack.size() > 0 && k > 0 && c < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // 如果k还大于0，这时还没删够，从末尾删去其余的就行
        // 12345 k = 2
        // 经过上一个循环还是12345 这是删掉最后两位即可
        for (int i = 0; i < k; i++) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
