import java.util.Random;

public class QuickerSortServer implements CommandExecutor {
    public static Random rng = new Random();
    int THRESH_HOLD = 50;

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

    void quickerSort(int[] arr, int left, int right) {
        if (right - left < THRESH_HOLD)
            insertionSort(arr, left, right);
        else {
            int pivotIndex = partition (arr, left, right);
            quickerSort(arr, left, pivotIndex - 1);
            quickerSort(arr, pivotIndex+1 , right);
        }
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

    private String quickerSortTime(){
        int size = 1000000;

        int[] array = new int[size];
        for(int i = 0; i < size; ++i) array[i] = rng.nextInt(size);
        long end, start = System.currentTimeMillis();
        quickerSort(array, 0, array.length-1);
        end = System.currentTimeMillis();
        return "" + (end - start);
        //return " Zeit: " + (end - start) + "\n";
    }

    private String quickerSortOptimization(){
        StringBuffer result = new StringBuffer();
        for (int i = 5; i <= 500; i += 5){
            THRESH_HOLD = i;
            //result.append("Threshold: " + THRESH_HOLD + quickerSortTime());
            result.append(THRESH_HOLD + ", " + quickerSortTime() + "\n");
        }
        return result.toString();
    }

    @Override
    public String execute(String command) throws Exception {
        return quickerSortOptimization();
    }
}
