package ch.zhaw.ads.Praktikum_12;

import ch.zhaw.ads.CommandExecutor;

import java.util.Random;

import static ch.zhaw.ads.Praktikum_12.QuickSortServer.partition;

public class NaiveParallelQuicksort2 extends Thread implements CommandExecutor {
    private final int SPLIT_THRESHOLD = 100000;
    final static int ARRSIZE = 100000;
    private int[] arr;
    private int left;
    private int right;

    public NaiveParallelQuicksort2() {
    }

    public NaiveParallelQuicksort2(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
        System.out.println("Ich wurde erstellt");
    }

    public void run() {
        System.out.println("ich fange an");
        int mid = 0;
        Thread t1 = null;
        Thread t2 = null;
        if (left < right) {
            mid = partition(arr, left, right);
            if (mid - left > SPLIT_THRESHOLD) {
                t1 = new NaiveParallelQuicksort2(arr, left, arr.length - 1);
                t1.start();
            } else {
                QuickSortServer.quickSort(arr, left, arr.length - 1);
            }
            if (right - mid > SPLIT_THRESHOLD) {
                t2 = new NaiveParallelQuicksort2(arr, mid, right);
                t2.start();
            } else {
                QuickSortServer.quickSort(arr, mid, right);
            }
            if (t1 != null) {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (t2 != null) {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String execute(String command) throws Exception, Throwable {
        arr = new int[ARRSIZE];
        Random randomNumberGenerator = new Random();

        for (int i = 0; i < ARRSIZE; i++) arr[i] = randomNumberGenerator.nextInt(ARRSIZE*5);
        this.left = 0;
        this.right = arr.length-1;
        this.start();
        return "Done";
    }
}
