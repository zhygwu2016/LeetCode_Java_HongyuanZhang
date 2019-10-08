package leetcode.java;

// 78. Subsets
// https://leetcode.com/problems/subsets/

import java.util.ArrayList;
import java.util.List;

public class LC78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) return result;

        result.add(new ArrayList<>());
        getResult(result, nums, 0, new ArrayList<>());

        return result;
    }

    private void getResult(List<List<Integer>> result, int[] nums, int level, List<Integer> list){
        if(level == nums.length) return;

        for(int i = level; i < nums.length; i++){
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            getResult(result, nums, i + 1, list);
            list.remove(list.size() - 1);
        }
        return;
    }
}
