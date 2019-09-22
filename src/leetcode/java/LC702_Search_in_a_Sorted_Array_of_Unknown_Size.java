package leetcode.java;

public class LC702_Search_in_a_Sorted_Array_of_Unknown_Size {

}

// 702. Search in a Sorted Array of Unknown Size
// https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

/*
public class LC702_Search_in_a_Sorted_Array_of_Unknown_Size {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 16;
        // Find the range first, then use binary search
        while(reader.get(right) < target){
            left = right;
            right *= 2;
        }

        return binarySearch(reader, left, right, target);
    }

    private int binarySearch(ArrayReader reader, int left, int right, int target){
        if(reader.get(left) == target) return left;
        if(reader.get(right) == target) return right;

        while(left + 1 < right){
            int mid = left + (right - left)/2;

            if(reader.get(mid) == target) return mid;
            else if(reader.get(mid) < target) left = mid;
            else right = mid;
        }

        if(reader.get(left) == target) return left;
        return reader.get(right) == target ? right : -1;
    }
}

 */
