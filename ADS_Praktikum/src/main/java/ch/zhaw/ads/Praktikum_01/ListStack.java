package ch.zhaw.ads.Praktikum_01;

/**
 * ch.zhaw.ads.Praktikum_01.BracketServer -- Praktikum Einführung in Algorithmen
 * Aufgabe 2 - Stack
 *
 * @author Tim Rhomberg
 * @version 1.0, 17.09.2000
 */
public class ListStack {
    Object[] data;
    private int top;
    private int capacity;

    public ListStack(int capacity) {
        this.capacity = capacity;
        removeAll();
    }

    public void push(Object x) {
        if (isFull()) {
            System.err.println("Stack schon voll");
        } else {
            data[top] = x;
            top++;
        }
    }

    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        top--;
        Object topItem = data[top];
        data[top] = null;
        return topItem;
    }

    public boolean isEmpty() {
        return (top == 0);
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return data[top - 1];
    }

    public void removeAll() {
        data = new Object[capacity];
        top = 0;
    }

    public boolean isFull() {
        return top == data.length;
    }
}
