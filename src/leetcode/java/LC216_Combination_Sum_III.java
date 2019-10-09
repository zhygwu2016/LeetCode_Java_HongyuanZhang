package leetcode.java;

// 216. Combination Sum III
// https://leetcode.com/problems/combination-sum-iii/

import java.util.ArrayList;
import java.util.List;

public class LC216_Combination_Sum_III {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if(n < 0 || k > n ) return result;

        List<Integer> list = new ArrayList<>();

        getResult(n, k, result, list, 1);

        return result;
    }

    private void getResult(int n, int k, List<List<Integer>> result,
                           List<Integer> list, int index){
        if(n < 0 || list.size() > k) return;

        if(list.size() == k && n == 0){
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i <= 9; i++){
            list.add(i);
            getResult(n - i, k, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
