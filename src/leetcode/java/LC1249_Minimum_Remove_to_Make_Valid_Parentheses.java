package leetcode.java;

// LC1249. Minimum Remove to Make Valid Parentheses
// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Using Stack
// 遇见右括号，和Stack里的左括号匹配抵消，stack里没有的话，就删掉这个右括号
// 遍历之后stack里剩下的左括号也是要被删掉的括号
public class LC1249_Minimum_Remove_to_Make_Valid_Parentheses {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) return "";

        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            indexesToRemove.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
