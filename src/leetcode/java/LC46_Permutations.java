package leetcode.java;

// 46. Permutations
// https://leetcode.com/problems/permutations/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
