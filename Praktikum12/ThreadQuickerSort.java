import java.util.concurrent.RecursiveAction;

public class ThreadQuickerSort extends RecursiveAction {

    private int THRESH_HOLD = 35;
    private int[] array;
    private int left;
    private int right;

    public ThreadQuickerSort(int[] array, int left, int right){
        this.array = array;
        this.left = left;
        this.right = right;
    }

    private int partition(int[] array, int left, int right){
        int middle = (left + right)/2;
        int pivot = array[middle];
        while (left <= right){
            while (array[left] < pivot) ++left;
            while (array[right] > pivot) --right;
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    private void swap(int[] array, int pos1, int pos2){
        int temp = array[pos2];
        array[pos2] = array[pos1];
        array[pos1] = temp;
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int k = left; k < right; k++) {
            int x = array[k];
            int i;
            for (i = k; ((i > 0) && (array[i-1] > x)); i--) {
                array[i] = array[i-1];
            }
            array[i] = x;
        }
    }

    @Override
    protected void compute() {
        if (right - left < THRESH_HOLD)
            insertionSort(array, left, right);
        else {
            int pivotIndex = partition (array, left, right);
            invokeAll(
                new ThreadQuickerSort(array, left, pivotIndex - 1),
                new ThreadQuickerSort(array, pivotIndex + 1 , right)
            );
        }
    }
}
