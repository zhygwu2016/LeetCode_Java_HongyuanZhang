package leetcode.java;

// 72. Edit Distance
// https://leetcode.com/problems/edit-distance/

public class LC72_Edit_Distance {
    public int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null) return 0;
        if(word1 == null || word2 == null){
            return word1 == null ? word2.length() : word1.length();
        }

        int m = word1.length(), n = word2.length();

        int[][] result = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++){
            result[i][0] = i;
        }
        for(int i = 0; i <= n; i++){
            result[0][i] = i;
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(word1.charAt(i) == word2.charAt(j)){
                    result[i + 1][j + 1] = result[i][j];
                }else{
                    result[i + 1][j + 1] = Math.min(Math.min(result[i][j + 1], result[i + 1][j]), result[i][j]) + 1;
                }
            }
        }

        return result[m][n];
    }
}
