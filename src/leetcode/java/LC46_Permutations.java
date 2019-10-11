package leetcode.java;

// 46. Permutations
// https://leetcode.com/problems/permutations/

import java.lang.reflect.Array;
import java.util.*;

public class LC46_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums == null || nums.length == 0) return result;

        getResult(nums, result, new ArrayList<>(), new HashSet<Integer>(), 0);

        return result;
    }

    private void getResult(int[] nums, List<List<Integer>> result,
                           List<Integer> list, Set<Integer> set, int index){
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) continue;
            list.add(nums[i]);
            set.add(nums[i]);
            getResult(nums, result, list, set, i);
            list.remove(list.size() - 1);
            set.remove(nums[i]);
        }
    }
}


class LC46_Permutations_2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums == null || nums.length == 0) return result;

        ArrayList<Integer> nums_list = new ArrayList<Integer>();

        for(int num : nums){
            nums_list.add(num);
        }

        permutations(nums_list, result, 0);

        return result;
    }

    private void permutations(ArrayList<Integer> nums,
                              List<List<Integer>> result, int index){
        if(index == nums.size() - 1){
            result.add(new ArrayList<Integer>(nums));
            return;
        }

        for(int i = index; i < nums.size(); i++){
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, index, i);
            // use next integers to complete the permutations
            permutations(nums, result, index + 1);
            // backtrack
            Collections.swap(nums, index, i);
        }
    }
}
