package leetcode.java;

// 151. Reverse Words in a String
// https://leetcode.com/problems/reverse-words-in-a-string/

public class LC151_Reverse_Words_in_a_String {
    public String reverseWords(String s) {
        if(s == null || s.length() <= 0) return s;

        String str = clearExtraSpace(s);
        String reversedStr = reverse(str);
        String words = reverseEachWord(reversedStr);

        return cleanSpace(words.toCharArray());
    }

    private String clearExtraSpace(String s){
        int left = 0, right = s.length() - 1;
        while(left < s.length() && s.charAt(left) == ' ') left++;
        while(right >= 0 && s.charAt(right) == ' ') right--;

        if(left > right) return "";
        return s.substring(left, right + 1);
    }

    private String reverse(String s){
        if(s.length() == 0) return "";
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;

        while(left < right){
            swap(arr, left++, right--);
        }
        return new String(arr);
    }

    private String reverseEachWord(String s){
        if(s.length() == 0) return "";
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == ' ') continue;
            else if(arr[i] != ' '){
                int j = i + 1;
                while(j < arr.length && arr[j] != ' '){
                    j++;
                }
                int left = i, right = j - 1;

                while(left < right){
                    swap(arr, left++, right--);
                }
                i = j;
            }
        }
        return new String(arr);
    }

    private String cleanSpace(char[] a){
        int i = 0, j = 0, n = a.length;
        while(j < n){
            while(j < n && a[j] == ' ') j++;             // skip spaces
            while(j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while(j < n && a[j] == ' ') j++;             // skip spaces
            if(j < n) a[i++] = ' ';                      // keep only one space
        }
        return new String(a).substring(0, i);
    }

    private void swap(char[] s, int left, int right){
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}
