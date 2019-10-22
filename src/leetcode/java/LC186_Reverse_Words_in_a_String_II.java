package leetcode.java;

// 186. Reverse Words in a String II
// https://leetcode.com/problems/reverse-words-in-a-string-ii/

public class LC186_Reverse_Words_in_a_String_II {
    public void reverseWords(char[] s) {
        if(s == null || s.length <= 1) return;

        reverseArr(s);
        reverseWord(s);
    }

    private void reverseArr(char[] str){
        int left = 0, right = str.length - 1;

        while(left < right){
            swap(str, left++, right--);
        }
    }

    private void reverseWord(char[] str){
        for(int i = 0; i < str.length; i++){
            if(str[i] != ' '){
                int j = i + 1;
                while(j < str.length && str[j] != ' '){
                    j++;
                }

                int left = i, right = j - 1;
                while(left < right){
                    swap(str, left++, right--);
                }
                i = j;
            }
        }
    }

    private void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
