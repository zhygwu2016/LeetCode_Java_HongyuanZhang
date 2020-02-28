package leetcode.java;

// LC301. Remove Invalid Parentheses
// https://leetcode.com/problems/remove-invalid-parentheses/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC301_Remove_Invalid_Parentheses {
    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> set = new HashSet<>();
        int rmL = 0, rmR = 0;

        // 应该删的左括号右括号个数
        for (char c : s.toCharArray()) {
            if (c == '(') {
                rmL++;
            } else if (c == ')') {
                if (rmL > 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }

        DFS(set, s, 0, rmL, rmR, 0, new StringBuilder());
        return new ArrayList<String>(set);
    }

    private void DFS(Set<String> set, String s, int i, int rmL, int rmR, int delta, StringBuilder sb) {
        if (i == s.length() && rmL == 0 && rmR == 0 && delta == 0) {
            set.add(sb.toString());
            return;
        }

        if (i >= s.length() || rmL < 0 || rmR < 0 || delta < 0)  return;

        char c = s.charAt(i);

        if (c == '(') {
            // remove '('
            DFS(set, s, i + 1, rmL - 1, rmR, delta, sb);

            // keep '('
            sb.append('(');
            DFS(set, s, i + 1, rmL, rmR, delta + 1, sb);
            sb.setLength(sb.length() - 1);
        } else if (c == ')') {
            // remove ')'
            DFS(set, s, i + 1, rmL, rmR - 1, delta, sb);

            // keep ')'
            sb.append(')');
            DFS(set, s, i + 1, rmL, rmR, delta - 1, sb);
            sb.setLength(sb.length() - 1);
        } else {
            sb.append(c);
            DFS(set, s, i + 1, rmL, rmR, delta, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
