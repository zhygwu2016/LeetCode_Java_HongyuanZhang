package leetcode.java;

// 275. H-Index II
// https://leetcode.com/problems/h-index-ii/

public class LC275_H_Index_II {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        if(citations.length == 1) return citations[0] == 0 ? 0 : 1;

        int left = 0, right = citations.length -1, len = citations.length;

        while(left <= right){
            int mid = left + (right - left)/2;

            // here the tricky part is compare citations[mid] and len - mid
            if(citations[mid] == len - mid){
                return len - mid;
            }else if(citations[mid] < len - mid){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        // use the third method, citations[right] will less than citations[left] after the loop
        return len - (right + 1);
        // return len - left;
    }
}
