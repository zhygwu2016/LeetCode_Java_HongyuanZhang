package leetcode.java;

// 22. Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/

import java.util.ArrayList;
import java.util.List;

public class LC22_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if(n <= 0) return result;

        getResult(n, n, new StringBuilder(), result);

        return result;
    }

    private void getResult(int left, int right, StringBuilder sb, List<String> result){
        if(left == 0 && right == 0){
            result.add(sb.toString());
            return;
        }

        if(left > right) return;

        if(left > 0){
            sb.append('(');
            getResult(left - 1, right, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(right > 0 && left < right){
            sb.append(')');
            getResult(left, right - 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

class LC22_Generate_Parentheses_solution2 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if(n <= 0) return result;

        DFS(n, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void DFS(int n, int l, int r, StringBuilder sb, List<String> result){
        // base case
        if(l + r == 2*n){
            result.add(sb.toString());
            return;
        }

        if(l < n){
            sb.append('(');
            DFS(n, l + 1, r, sb, result);
            sb.deleteCharAt(sb.length() - 1); // remove() setLength
        }

        if(r < l){
            sb.append(')');
            DFS(n, l, r + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

class LC22_AlgorithmAdvanced {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        List<String> res = new ArrayList<>();
        DFS(res, new StringBuilder(), 0, 0, n, 0);
        return res;
    }

    private void DFS(List<String> res, StringBuilder sb, int l, int r, int n, int step) {
        if (step == 2 * n && l == r) {
            String s = sb.toString();
            res.add(s);
            return;
        }

        if (step > 2 * n || l > n || l < r) return;

        if (l < n) {
            sb.append("(");
            DFS(res, sb, l + 1, r, n, step + 1);
            sb.setLength(sb.length() - 1);
        }
        if (l > r) {
            sb.append(")");
            DFS(res, sb, l, r + 1, n, step + 1);
            sb.setLength(sb.length() - 1);
        }
    }
}
