package leetcode.java;

// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/

import java.util.PriorityQueue;

// Quickselect
public class LC215_Kth_Largest_Element_in_an_Array {
    public int findKthLargest(int[] nums, int k){
        if(nums == null || nums.length == 0 || k <= 0) return -1;

        return quickSelection(nums, 0, nums.length - 1, nums.length - k);
    }

    // Use quick selection to find position
    private int quickSelection(int[] nums, int begin, int end, int k){
        if(begin > end) return Integer.MAX_VALUE;

        int pivot = nums[end];
        int index = begin;

        for(int i = begin; i < end; i++){
            if(nums[i] <= pivot){
                swap(nums, index++, i);
            }
        }

        swap(nums, index, end);

        if(index == k) return nums[k];
        else if(index < k) return quickSelection(nums, index + 1, end, k);
        else return quickSelection(nums, begin, index - 1, k);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Heap
class LC215_2 {
    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }
}
