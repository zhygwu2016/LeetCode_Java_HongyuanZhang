package leetcode.java;

// 5. Longest Palindromic Substring
// https://leetcode.com/problems/longest-palindromic-substring/

public class LC5_Longest_Palindromic_Substring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() <= 1) return s;

        int[] max = new int[1];
        int[] result = new int[2];

        for(int i = 0; i < s.length() - 1; i++){
            findMax(s, i, i, max, result);
            findMax(s, i, i + 1, max, result);
        }

        return s.substring(result[0], result[1]);
    }

    private void findMax(String s, int left, int right, int[] max, int[] result){
        while(left >= 0 && right < s.length()){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else{
                break;
            }
        }

        int dist = right - left - 1;

        if(dist > max[0]){
            max[0] = dist;
            result[0] = left + 1;
            result[1] = right;
        }
    }
}
