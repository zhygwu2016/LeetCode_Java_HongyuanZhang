package leetcode.java;

public class LC278_First_Bad_Version {
}

// 278. First Bad Version
// https://leetcode.com/problems/first-bad-version/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
/*
public class LC278_First_Bad_Version extends VersionControl {
    public int firstBadVersion(int n) {
        if(n <= 0) return 0;
        if(n == 1) return isBadVersion(n) ? 1 : 0;

        int left = 1, right = n;

        while(left + 1 < right){
            int mid = left + (right - left)/2;

            // just the normal binary search with an API
            if(isBadVersion(mid)){
                right = mid;
            }else{
                left = mid;
            }
        }

        return isBadVersion(left) ? left : right;
    }
}

 */

