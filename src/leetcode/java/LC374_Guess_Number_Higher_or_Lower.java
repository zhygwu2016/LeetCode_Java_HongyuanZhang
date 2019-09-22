package leetcode.java;

public class LC374_Guess_Number_Higher_or_Lower {

}

// 374. Guess Number Higher or Lower
// https://leetcode.com/problems/guess-number-higher-or-lower/

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

/*
public class LC374_Guess_Number_Higher_or_Lower extends GuessGame {
    // "My Number" --> means target number, not the number in  the input
    public int guessNumber(int n) {
        int left = 1, right = n;

        while(left + 1 < right){
            int mid = left + (right - left)/2;

            if(guess(mid) == 0) return mid;
            else if(guess(mid) == -1) right = mid;
            else left = mid;
        }
        return guess(left) == 0 ? left : right;
    }
}

 */

