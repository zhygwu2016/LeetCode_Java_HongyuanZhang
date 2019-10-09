package leetcode.java;

// 77. Combinations
// https://leetcode.com/problems/combinations/

import java.util.ArrayList;
import java.util.List;

public class LC77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(n <= 0 || k > n) return result;

        getResult(n, k, 1, new ArrayList<>(), result);

        return result;
    }

    private void getResult(int n, int k, int index, List<Integer> list, List<List<Integer>> result){
        if(list.size() >= k){
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i <= n; i++){
            list.add(i);
            getResult(n, k, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }
}
