package leetcode.java;

// 13. Roman to Integer
// https://leetcode.com/problems/roman-to-integer/

public class LC13_Roman_to_Integer {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0) return 0;

        int[] vals = new int[s.length()];

        for(int i = 0; i < vals.length; i++){
            char c = s.charAt(i);

            switch(c){
                case 'M' : vals[i] = 1000;
                            break;
                case 'D' : vals[i] = 500;
                            break;
                case 'C' : vals[i] = 100;
                            break;
                case 'L' : vals[i] = 50;
                            break;
                case 'X' : vals[i] = 10;
                            break;
                case 'V' : vals[i] = 5;
                            break;
                case 'I' : vals[i] = 1;
                            break;
            }
        }

        int result = 0;

        for(int i = 0 ; i < vals.length; i++){
            if(i == vals.length - 1){
                result += vals[i];
            }else if(vals[i] < vals[i + 1]){
                result -= vals[i];
            }else{
                result += vals[i];
            }
        }

        return result;
    }
}
