package leetcode.java;

// LC47. Permutations II
// https://leetcode.com/problems/permutations-ii/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC47_Permutations_II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)  return res;

        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();
        dfs(nums, used, list, res);

        return res;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            // duplicate
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            list.add(nums[i]);
            dfs(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
