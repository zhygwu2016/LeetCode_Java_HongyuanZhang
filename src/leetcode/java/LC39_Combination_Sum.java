package leetcode.java;

// 39. Combination Sum
// https://leetcode.com/problems/combination-sum/

import java.util.ArrayList;
import java.util.List;

public class LC39_Combination_Sum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(candidates == null || candidates.length == 0) return result;

        List<Integer> list = new ArrayList<>();

        getResult(candidates, result, list, target, 0, 0);

        return result;
    }

    private void getResult(int[] candidates, List<List<Integer>> result,
                           List<Integer> list, int target, int sum, int index){
        if(sum > target || index >= candidates.length) return;

        if(sum == target){
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i < candidates.length; i++){
            list.add(candidates[i]);
            getResult(candidates, result, list, target, sum + candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
