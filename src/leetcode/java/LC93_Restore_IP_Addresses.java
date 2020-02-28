package leetcode.java;

// LC93. Restore IP Addresses
// https://leetcode.com/problems/restore-ip-addresses/

import java.util.ArrayList;
import java.util.List;

public class LC93_Restore_IP_Addresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();

        if (s == null || s.length() == 0) return res;

        addIP(res, new StringBuilder(), 0, s, 0);

        return res;
    }

    private void addIP(List<String> res, StringBuilder path, int index, String s, int num){
        if (num == 4) {
            if (index == s.length()) {
                path.setLength(path.length() - 1);
                res.add(path.toString());
            }
            return;
        }

        int length = path.length();
        for (int l = 1; l <= 3; l++) {
            if (index + l > s.length()) break;

            int val = Integer.valueOf(s.substring(index, index + l));
            if (val <= 255) {
                path.append(val + ".");
                addIP(res, path, index + l, s, num + 1);
                path.setLength(length);
            }
            // 如果0开头，不应该继续循环。"012"是invalid
            if (val == 0) {
                break;
            }
        }
    }
}
