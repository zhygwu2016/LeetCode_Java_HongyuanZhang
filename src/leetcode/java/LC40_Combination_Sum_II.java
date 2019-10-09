package leetcode.java;

// 40. Combination Sum II
// https://leetcode.com/problems/combination-sum-ii/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC40_Combination_Sum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(candidates == null || candidates.length == 0) return result;

        Arrays.sort(candidates);

        List<Integer> list = new ArrayList<>();

        getResult(candidates, result, list, target, 0);

        return result;
    }

    private void getResult(int[] candidates, List<List<Integer>> result,
                           List<Integer> list, int target, int index){
        if(target < 0) return;

        if(target == 0){
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i < candidates.length; i++){
            if(i > index && candidates[i] == candidates[i - 1]) continue;

            list.add(candidates[i]);
            getResult(candidates, result, list, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
