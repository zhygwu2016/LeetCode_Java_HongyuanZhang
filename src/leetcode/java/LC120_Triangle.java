package leetcode.java;

// 120. Triangle
// https://leetcode.com/problems/triangle/

import java.util.List;

public class LC120_Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0;
        int size = triangle.size();
        int[] minDepth = new int[size + 1];

        for(int i = size - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                minDepth[j] = Math.min(minDepth[j], minDepth[j + 1]) + triangle.get(i).get(j);
            }
        }

        return minDepth[0];
    }
}
