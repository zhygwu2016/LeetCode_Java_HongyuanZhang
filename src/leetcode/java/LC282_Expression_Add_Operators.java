package leetcode.java;

// LC282. Expression Add Operators
// https://leetcode.com/problems/expression-add-operators/

import java.util.ArrayList;
import java.util.List;

public class LC282_Expression_Add_Operators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }

        DFS(res, num, 0, 0, new StringBuilder(), target);
        return res;
    }

    private void DFS(List<String> res, String num, long last, long curVal, StringBuilder sb, int target) {
        int len = num.length();
        if (len == 0 && curVal == target) {
            res.add(sb.toString());
            return;
        }

        if (len == 0) return;

        int lenSb = sb.length();
        long val = 0;
        for (int i = 0; i < len; i++) {
            val = val * 10 + (num.charAt(i) - '0');

            if (sb.length() != 0) {
                DFS(res, num.substring(i + 1), val, curVal + val, sb.append("+").append(val), target);
                sb.setLength(lenSb);

                DFS(res, num.substring(i + 1), -val, curVal - val, sb.append("-").append(val), target);
                sb.setLength(lenSb);

                DFS(
                        res,
                        num.substring(i + 1),
                        last * val,
                        (curVal - last) + last * val,
                        sb.append("*").append(val),
                        target
                );
                sb.setLength(lenSb);

            } else {
                DFS(res, num.substring(i + 1), val, curVal + val, sb.append(val), target);
                sb.setLength(lenSb);
            }
            if (val == 0) break;
        }
    }
}
