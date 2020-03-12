package leetcode.java;

// LC384. Shuffle an Array
// https://leetcode.com/problems/shuffle-an-array/

import java.util.Random;

public class LC384_Shuffle_an_Array {

    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public LC384_Shuffle_an_Array(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }
}

class Solution_LC384 {
    private int[] nums;

    public Solution_LC384(int[] nums) {
        this.nums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            this.nums[i] = nums[i];
        }
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] ret = new int[this.nums.length];

        for (int i = 0; i < nums.length; i++) {
            int rand = new Random().nextInt(i + 1);

            ret[i] = ret[rand];
            ret[rand] = nums[i];
        }

        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
