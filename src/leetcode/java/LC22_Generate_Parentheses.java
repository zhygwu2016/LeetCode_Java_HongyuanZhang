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
