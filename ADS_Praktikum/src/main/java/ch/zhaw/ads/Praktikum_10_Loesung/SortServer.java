package ch.zhaw.ads.Praktikum_10_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;

public class SortServer implements CommandExecutor {
    private final int DATARANGE = 1000000;
    private final int DISTR = 0;
    private String[] SORTMETHODS = {"BUBBLE","INSERTION","QUICK","SELECTION"};

    public void swap(int[] a, int i, int j) {
        int h = a[i]; a[i]= a[j]; a[j] = h;
    }

    public void bubbleSort(int[] a) {
        for (int k = a.length-1; k > 0; k--) {
            boolean noSwap = true;
            for (int i = 0; i < k; i++) {
                if (a[i] > a[i+1]) {
                    swap(a,i,i+1);
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    void insertionSort(int[] a){
        for (int k = 1; k < a.length; k++)
            if (a[k] < a[k-1]){
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i-1] > x));i--)
                    a[i] = a[i-1];
                a[i] = x;
            }
    }

    void selectionSort(int[] a){
        for (int k = 0; k < a.length; k++){
            int min = k;
            for (int i = k+1; i < a.length; i ++) {
                if (a[i] < a[min]) min = i;
            }
            if (min != k) swap (a, min, k);
        }
    }

    private boolean isSorted(int[] a) {
        for (int i = 0; i < a.length-1; i ++)
            if (a[i] > a[i+1]) return false;
        return true;
    }

    void randomData(int[] a) {
        for (int i = 0; i < a.length; i++)
            a[i] = (int)(Math.random()*DATARANGE);
    }

    void ascendingData(int[] a) {
        for (int i = 0; i < a.length; i++) a[i] = i;
    }

    void descendingData(int[] a) {
        for (int i = 0; i < a.length; i++) a[i] = a.length-i;
    }


    public String execute(String arg) {
        StringTokenizer tok = new StringTokenizer(arg);
        StringBuffer result = new StringBuffer();

        while (tok.hasMoreTokens()) {
            // which sort method?
            int sort = Arrays.binarySearch(SORTMETHODS,(String)tok.nextToken());

            // prepare data
            int elems = Integer.parseInt(tok.nextToken());
            int[] a = new int[elems];
            int[] b = new int[elems];
            switch(DISTR) {
                case 0 : randomData(a); break;
                case 1 : ascendingData(a); break;
                case 2 : descendingData(a); break;
            }

            // do the sort
            long startTime = System.currentTimeMillis();
            long endTime = startTime;
            int count=0;
            while (endTime < startTime+1000) {
                System.arraycopy(a,0,b,0,a.length);
                switch(sort) {
                    case 0 : bubbleSort(b); break;
                    case 1 : insertionSort(b);break;
                    case 2 : // quickSort(b);break;
                    case 3 : selectionSort(b);break;
                }
                count++;
                endTime = System.currentTimeMillis();
            }
            if (!isSorted(b)) return "PANIC!!!\n";
            result.append(SORTMETHODS[sort]+"\tAnz. Elemente: "+elems);
            result.append("\tLaufzeit: "+ Double.toString((double)(endTime-startTime)/count));
            result.append("\n");
        }
        return result.toString();
    }
}