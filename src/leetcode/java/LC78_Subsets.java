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

// 第二类搜索树
class LC78_Subsets_2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) return result;

        getResult(nums, new ArrayList<>(), 0, result);

        return result;
    }

    private void getResult(int[] nums, List<Integer> list, int index, List<List<Integer>> result){
        if(index == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[index]);
        getResult(nums, list, index + 1, result);
        list.remove(list.size() - 1);
        getResult(nums, list, index + 1, result);
    }
}
