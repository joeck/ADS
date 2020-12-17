package ch.zhaw.ads.Praktikum_12;

import ch.zhaw.ads.CommandExecutor;

import java.util.Random;

public class QuickerSortServer implements CommandExecutor {
    final static int ARRSIZE = 100000;
    final static int QSTHREASHHOLD = 50;


    public String execute(String arg) {
        StringBuilder result = new StringBuilder();
        int[] arr = new int[ARRSIZE];
        Random randomNumberGenerator = new Random();

        for (int i = 0; i < ARRSIZE; i++) arr[i] = randomNumberGenerator.nextInt(ARRSIZE*5);
        long endTime, startTime = System.currentTimeMillis();
        quickerSort(arr);
        endTime = System.currentTimeMillis();
        for (int i = 0; i < ARRSIZE; i++) { result.append(arr[i]).append("\n");}
        result.append("Laufzeit (ms): ").append(1.0 * (endTime - startTime)).append("\n");
        return result.toString();
    }


    private static void quickerSort(int[] a){
        quickerSort(a, 0, a.length-1);
    }

    static void quickerSort(int[] a, int left, int right) {
        if (right - left < QSTHREASHHOLD)
            insertionSort(a, left, right);
        else {
            int l = partition(a, left, right);
            quickerSort(a, left, l - 1);
            quickerSort(a, l, right);
        }
    }

    private static void insertionSort(int[] a, int left, int right) {
        int steps = 0;
        for(int i = left + 1; i <= right; i++) {
            if(a[i - 1] > a[i]) {
                int temp = a[i];
                int x;
                for(x = i; (x > 0) && (a[x - 1] > temp); x--) {
                    a[x] = a[x-1];
                }
                a[x] = temp;
            }
        }
    }

    private static int partition (int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) { left++; }
            while (arr[right] > pivot) { right--; }
            if (left <= right) {
                swap(arr,left,right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] arr, int i, int j)
    {
        int swapElement;
        swapElement = arr[i];
        arr[i] = arr[j];
        arr[j] = swapElement;
    }
}
