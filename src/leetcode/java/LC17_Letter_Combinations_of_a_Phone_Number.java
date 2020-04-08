package leetcode.java;

// LC17. Letter Combinations of a Phone Number
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17_Letter_Combinations_of_a_Phone_Number {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return output;
        }

        helper("", digits, output);

        return output;
    }

    private void helper(String combination, String next_digits, List<String> output) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
            return;
        }

        String digit = next_digits.substring(0, 1);
        String letters = phone.get(digit);

        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);
            // append the current letter to the combination
            // and proceed to the next digits
            helper(combination + letter, next_digits.substring(1), output);
        }

    }
}
