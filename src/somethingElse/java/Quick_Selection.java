package somethingElse.java;

public class Quick_Selection {
    // e.g. find Kth smallest
    public int findKth(int[] nums, int k){
        if(nums == null || nums.length == 0 || k <= 0) return -1;

        return quickSelection(nums, 0, nums.length - 1, k - 1);
        // 第k小，所以找index是k - 1
    }

    // Use quick selection to find position
    private int quickSelection(int[] nums, int begin, int end, int k){
        if (begin > end) return Integer.MAX_VALUE;

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

// 算法加强
class Quick_Selection_Algorithm_Advanced {
    public int findKth(int[] nums, int k) {
        int len = nums.length;
        return findKth(nums, 0, len - 1, k);
    }

    private int findKth(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }

        int q = partition(nums, start, end);
        int rank = q - start + 1;
        if (rank == k) {
            return nums[q];
        } else if (rank < k) {
            return findKth(nums, q + 1, end, k - rank);
        } else { // rank > k
            return findKth(nums, start, q - 1, k);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, ++left, i);
            }
        }
        swap(nums, ++left, end);
        return left;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
