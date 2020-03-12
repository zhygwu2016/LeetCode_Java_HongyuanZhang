package leetcode.java;

public class LC10_Regular_Expression_Matching {
    public boolean isMatch(String s, String p) {
        if(s == null && p == null) return true;
        if(s == null || p == null) return false;
        if(p.length() == 0) return s.length() == 0;

        if(p.length() > 1 && p.charAt(1) == '*'){
            if(isMatch(s, p.substring(2))){
                return true;
            }else if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))){
                return isMatch(s.substring(1), p);
            }else{
                return false;
            }
        } else {
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))){
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        }
    }
}

// 算法加强，dfs, no pruning
class LC10_No_Pruning {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        return dfs(s, 0, p, 0);
    }

    private boolean dfs(String s, int idxS, String p, int idxP) {
        int lenP = p.length(), lenS = s.length();

        if (idxP == lenP) return idxS == lenS;

        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // 非*情况
            if (idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                return dfs(s, idxS + 1, p, idxP + 1);
            } else {
                return false;
            }
        } else { // *结构
            int i = idxS - 1;
            while(i < lenS
                    && (i < idxS
                        || p.charAt(idxP) == '.'
                        || p.charAt(idxP) == s.charAt(i))) { // 空, a, aa, aaa, ...
                if (dfs(s, i + 1, p, idxP + 2)) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
}

// 算法加强，dfs, add pruning
class LC10_Add_Pruning {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int lenP = p.length(), lenS = s.length();
        Boolean[][] mem = new Boolean[lenS + 1][lenP + 1];
        // boundary is lenS + 1 and lenS + 1 because in dfs() idxS is likely to be lenS

        return dfs(s, 0, p, 0, mem);
    }

    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] mem) {
        int lenP = p.length(), lenS = s.length();
        if (mem[idxS][idxP] != null) {
            return mem[idxS][idxP];
        }

        if (idxP == lenP) return idxS == lenS;

        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // 非*情况
            if (idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                mem[idxS][idxP] = dfs(s, idxS + 1, p, idxP + 1, mem);
            } else {
                mem[idxS][idxP] = false;
                return false;
            }
        } else { // *结构
            int i = idxS - 1;
            while(i < lenS
                    && (i < idxS
                    || p.charAt(idxP) == '.'
                    || p.charAt(idxP) == s.charAt(i))) { // 空, a, aa, aaa, ...
                if (dfs(s, i + 1, p, idxP + 2, mem)) {
                    mem[idxS][idxP] = true;
                    return true;
                }
                i++;
            }
            mem[idxS][idxP] = false;
        }
        return mem[idxS][idxP];
    }
}

// 算法加强，DP
class LC10_DP {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int lenP = p.length(), lenS = s.length();
        if (lenP == 0) return lenS == 0;
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];

        dp[lenS][lenP] = true;

        int k = lenP - 2;
        while(k >= 0) {
            if (p.charAt(k + 1) == '*') {
                dp[lenS][k] = true;
            } else {
                break;
            }
            k -= 2;
        }

        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    continue;
                }

                if (j + 1 >= lenP || p.charAt(j + 1) != '*') { // 非*情况
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else { // *情况
                    int idx = i - 1;
                    dp[i][j] = false;
                    while (idx < lenS
                            && (idx < i
                                || p.charAt(j) == '.'
                                || p.charAt(j) == s.charAt(idx))) {
                        if (dp[idx + 1][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        idx++;
                    }
                }
            }
        }

        return dp[0][0];
    }
}
