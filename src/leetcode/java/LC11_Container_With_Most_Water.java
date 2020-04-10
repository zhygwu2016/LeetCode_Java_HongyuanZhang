package leetcode.java;

// LC11. Container With Most Water
// https://leetcode.com/problems/container-with-most-water/

// two pointer
public class LC11_Container_With_Most_Water {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
