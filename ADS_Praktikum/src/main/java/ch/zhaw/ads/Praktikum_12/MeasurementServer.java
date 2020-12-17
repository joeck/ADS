package ch.zhaw.ads.Praktikum_12;

import ch.zhaw.ads.CommandExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MeasurementServer implements CommandExecutor {
    private final File times;
    private final File threashhold;
    final static int ARRSIZE = 100000;
    static double QSTHREASHHOLD = 1;
    private final int MAX_THREASHHOLD = 500;
    final boolean APPEND = true;
    static int index = 0;

    public MeasurementServer() {
        times = new File("times.log");
        threashhold = new File("threashhold.log");
    }

    @Override
    public String execute(String command) throws Throwable {
        while(QSTHREASHHOLD <= MAX_THREASHHOLD) {
            writeToFile(times, getTime(getRandomNumbersArray()));
            writeToFile(threashhold, QSTHREASHHOLD);
            QSTHREASHHOLD++;
        }
        return "Done.\n";
    }

    private void writeToFile(File fileToWrite, double value) {
        try(FileWriter fileWriter = new FileWriter(fileToWrite, APPEND)) {
            fileWriter.write(value + ";");
            index++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] getRandomNumbersArray() {
        int[] arr = new int[ARRSIZE];
        Random randomNumberGenerator = new Random();
        for (int i = 0; i < ARRSIZE; i++) arr[i] = randomNumberGenerator.nextInt(ARRSIZE*5);
        return arr;
    }

    private double getTime(int[] arr) {
        long endTime, startTime = System.currentTimeMillis();
        quickerSort(arr);
        endTime = System.currentTimeMillis();
        return 1.0 * (endTime - startTime);
    }

    private static void quickerSort(int[] a){
        quickerSort(a, 0, a.length - 1);
    }

    static void quickerSort(int[] a, int left, int right) {
        if (right - left < QSTHREASHHOLD)
            insertionSort(a, left, right);
        else {
            int l = partition (a,left,right);
            quickerSort(a, left, l-1);
            quickerSort(a, l , right); }
    }

    private static void insertionSort(int[] a, int left, int right) {
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

    static void InsertionSort(int[] a) {
        for (int k = 1; k < a.length; k++) {
            int x = a[k];
            int i;
            for (i = k; ((i > 0) && (a[i-1] > x)); i--)
                a[i] = a[i-1];
            a[i] = x;
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

    private static void swap(int[] arr, int i, int j) {
        int swapElement;
        swapElement = arr[i];
        arr[i] = arr[j];
        arr[j] = swapElement;
    }
}
