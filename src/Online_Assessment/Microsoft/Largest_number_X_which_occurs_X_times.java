package Online_Assessment.Microsoft;

// Largest number X which occurs X times
// https://leetcode.com/discuss/interview-question/525977/

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.HashMap;
import java.util.Map;

public class Largest_number_X_which_occurs_X_times {
    public int solution(int[] A) {
        // write your code in Java SE 8

        // Build a HashMap, key is the element in the array A,
        // value is the time of occurence of this element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            int cur = A[i];
            if (map.containsKey(cur)) {
                int val = map.get(cur);
                val++;
                map.put(cur, val);
            } else {
                map.put(cur, 1);
            }
        }

        int max = 0;
        // Traverse every key-value pair in this HashMap.
        // When key == value, we update 'max' is key is bigger.
        for (Map.Entry<Integer, Integer> entryList : map.entrySet()) {
            int key = entryList.getKey();
            int val = entryList.getValue();
            if (key == val && key > max) {
                max = key;
            }
        }

        // We return the biggest value X which occurrs X times.
        return max;
    }
}

// Time Complexity: O(N). N is the length of this array A.
//     First, we traverse the array A to build the HashMap. Time is O(N)
//     Then we traverse the HashMap, time is O(L), L is the size of HashMap.
//     Totally, time is O(N + L), and L is not bigger than N,
//     so the Time complexity in total is O(N).
// Space Complexity: O(L), L is the size of the HashMap we build.
//     The max value of L is N, so we can also say the space complexity is O(N).

