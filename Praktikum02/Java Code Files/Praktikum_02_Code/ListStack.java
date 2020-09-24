package Praktikum_02_Code;

import java.util.ArrayList;

public class ListStack<T> {
    int TOP_OF_STACK = 0;
    private final ArrayList<T> stack = new ArrayList<>();

    public void push(T item){
        stack.add(TOP_OF_STACK, item);
    }

    public T pop(){
        if(stack.isEmpty()) return null;
        return stack.remove(TOP_OF_STACK);
    }

    public boolean isFull(){
        return false; //unlimited stack
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public T peek(){
        T element = pop();
        push(element);
        return element;
    }

    public void empty(){
        stack.clear();
    }
}
