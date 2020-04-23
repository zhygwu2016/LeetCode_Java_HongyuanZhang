package somethingElse.java;

public class Quick_Sort {
    public void quickSort(int[] array, int left, int right) {
        if (array == null || array.length <= 1) return;
        if (right == left) return;
        if (left < right) { //must need
            //这里是把pivot两边的partition好，然后在分别对pivot的左边和右边call recursion
            int pivot = partition(array, left, right);
            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int start = left, end = right - 1;
        while (start <= end) {
            if (array[start] < pivot) start++;
            else if (array[end] > pivot) end--;
            else swap (array, start++, end--);
        }
        //这里pivot要记得和start换一下，因为最后是越过的
        if (start != right) swap(array, start, right);
        return start;
    }

    public void swap (int[] array, int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
