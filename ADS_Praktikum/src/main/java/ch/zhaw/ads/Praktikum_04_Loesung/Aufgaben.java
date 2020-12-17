package ch.zhaw.ads.Praktikum_04_Loesung;

public class Aufgaben {
}

// Aufgabe Folie 14
class ListNode {
    int val;
    ListNode next;

    private void PrintAscending(ListNode x) {
        System.out.println(x.val);
        if (next != null) PrintAscending(next);
    }

    private void PrintDescending(ListNode x) {
        if (next != null) PrintAscending(next);
        System.out.println(x.val);
    }
}

// Aufgabe Folie 36
class Fibonacci{

    static int FibonacciRecursiv(int i)
    {
        if(i<=0) // fuer negative Zahl auch 0!
            return 0;
        else if(i==1)
            return 1;
        else
            return FibonacciRecursiv(i-2)+FibonacciRecursiv(i-1);
    }

    static void FibonacciIterativ (int n)
    {
        int newFib = n;
        // prepare first iteration
        int prevFibMin1 = 1;
        int prevFibMin2 = 0;

        // from n = 2 the sum is calculated, otherwise n is returned
        for(int i=1; i < n ; i++) {
            newFib = prevFibMin1 + prevFibMin2;
            // prepare next iteration
            prevFibMin2 = prevFibMin1;
            prevFibMin1 = newFib;
        }
        System.out.println(newFib);
    }

}