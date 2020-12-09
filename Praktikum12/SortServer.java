
import java.util.Arrays;
import java.util.Random;

public class SortServer implements CommandExecutor {

    private int[] genRandomNumArray(int amount) {
        int[] array = new int[amount];
        Random random = new Random();

        for (int i = 0; i < array.length; ++i){
            array[i] = random.nextInt(1000000);
        }
        return array;
    }

    private boolean checkSorted(int[] array){
        boolean result = true;
        for (int i = 0; i < array.length -1; ++i) {
            if (array[i] > array[i+1]) {
                result = false;
                break;
            }
        }
        return result;
    }

    private void bubbleSort(int[] array) {
        for (int k = array.length - 1; k > 0; --k) {
            boolean noSwap = true;
            for (int i = 0; i < k; ++i) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    private void swap(int[] array, int pos1, int pos2){
        int temp = array[pos2];
        array[pos2] = array[pos1];
        array[pos1] = temp;
    }

    private void insertionSort(int[] array) {
        for (int k = 1; k < array.length; ++k) {
            int x = array[k];
            int i;
            for (i = k; ((i > 0) && (array[i-1] > x)); --i) {
                array[i] = array[i-1];
            }
            array[i] = x;
        }
    }

    private void selectionSort(int[] array) {
        for (int k = 0; k < array.length; ++k) {
            int min = k;
            for (int i = k +1; i < array.length; ++i) {
                if (array[i] < array[min]) min = i;
            }
            if (min != k) swap(array, min, k);
        }
    }

    private void timeBubbleSort(int amount) {
        long end, temp, start = System.currentTimeMillis();
        int count = 0;
        do {
            temp = System.currentTimeMillis();
            int[] array = genRandomNumArray(amount);
            temp = System.currentTimeMillis() - temp;
            bubbleSort(array);
            count++;
            end = System.currentTimeMillis();
            end = end - temp;
        } while (end - start < 1000);
        System.out.println("BubbleSort time="+(double)(end-start)/count);
    }

    private void timeSelectionSort(int amount) {
        long end, temp, start = System.currentTimeMillis();
        int count = 0;
        do {
            temp = System.currentTimeMillis();
            int[] array = genRandomNumArray(amount);
            temp = System.currentTimeMillis() - temp;
            selectionSort(array);
            count++;
            end = System.currentTimeMillis();
            end = end - temp;
        } while (end - start < 1000);
        System.out.println("SelectionSort time="+(double)(end-start)/count);
    }

    private void timeInsertionSort(int amount) {
        long end, temp, start = System.currentTimeMillis();
        int count = 0;
        do {
            temp = System.currentTimeMillis();
            int[] array = genRandomNumArray(amount);
            temp = System.currentTimeMillis() - temp;
            insertionSort(array);
            count++;
            end = System.currentTimeMillis();
            end = end - temp;
        } while (end - start < 1000);
        System.out.println("InsertionSort time="+(double)(end-start)/count);
    }

    @Override
    public String execute(String command) throws Exception {
        try {
            String[] words = command.split(" ");
            int amount = Integer.parseInt(words[1]);

            if (command.startsWith("INSERTION")) {
                timeInsertionSort(amount);
            } else if(command.startsWith("SELECTION")) {
                timeSelectionSort(amount);
            } else if(command.startsWith("BUBBLE")) {
                timeBubbleSort(amount);
            } else {
                return "invalid input\n";
            }
            int[] array = genRandomNumArray(amount);
            bubbleSort(array);
            return "\n" + checkSorted(array);
        } catch (Exception e){
            e.printStackTrace();
            return "usage: [INSERTION | SELECTION | BUBBLE] Amount\nexample: BUBBLE 10000\n";
        }
    }
}
