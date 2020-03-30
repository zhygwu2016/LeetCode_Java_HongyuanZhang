package leetcode.java;

// LC90. Subsets II
// https://leetcode.com/problems/subsets-ii/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Each recursion level focuses on all the following elements.
// We scan through all the following elements and decide whether to choose or not choose that element.
// (Every level split into N branches.)
public class LC90_Subsets_II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) return result;

        Arrays.sort(nums);
        result.add(new ArrayList<>());
        getResult(result, nums, 0, new ArrayList<>());

        return result;
    }

    private void getResult(List<List<Integer>> result, int[] nums, int level, List<Integer> list){
        if(level == nums.length) return;

        for(int i = level; i < nums.length; i++){
            if (i > level && nums[i] == nums[i - 1])  continue;
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            getResult(result, nums, i + 1, list);
            list.remove(list.size() - 1);
        }
        return;
    }
}

// Each recursion level focuses on one element,
// we need to decide choose or not choose this element.
// (Every level split into 2 branches.)
class Solution_LC90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0,false);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos, boolean choosePre) {
        if(pos==nums.length) {
            res.add(new ArrayList<>(ls));
            return;
        }
        helper(res,ls,nums,pos+1,false);
        if(pos>=1&&nums[pos]==nums[pos-1]&&!choosePre) return;
        ls.add(nums[pos]);
        helper(res,ls,nums,pos+1,true);
        ls.remove(ls.size()-1);
    }
}

class LC90_II {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) return result;

        Arrays.sort(nums);

        getResult(nums, new ArrayList<>(), 0, result, false);

        return result;
    }

    private void getResult(int[] nums, List<Integer> list, int index,
                           List<List<Integer>> result, boolean choosePrev){
        if(index == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[index]);
        getResult(nums, list, index + 1, result, false);
        list.remove(list.size() - 1);
        if (index > 0 && nums[index] == nums[index - 1] && !choosePrev) return;
        getResult(nums, list, index + 1, result, true);
    }
}


