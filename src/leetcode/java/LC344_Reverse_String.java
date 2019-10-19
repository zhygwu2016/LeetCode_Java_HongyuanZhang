package leetcode.java;

// 344. Reverse String
// https://leetcode.com/problems/reverse-string/

public class LC344_Reverse_String {
    public void reverseString(char[] s) {
        if(s == null || s.length <= 1) return;

        int left = 0, right = s.length - 1;

        while(left < right){
            swap(s, left++, right--);
        }
    }

    private void swap(char[] s, int left, int right){
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}
