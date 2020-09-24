package Praktikum_02_Code;

import javax.swing.text.html.ListView;
import java.util.AbstractList;

public class MyList extends AbstractList {

    private final ListNode header = new ListNode(null);

    public MyList() {
        header.next = header;
        header.prev = header;
    }

    @Override
    public Object get(int pos) {
        ListNode iter = header;
        for(int i = 0; i<=pos; ++i){
           iter = iter.next;
        }
        return iter.data;
    }

    @Override
    public int size() {
        int counter = 0;
        ListNode iter = header;
        while (iter.next != header){
            iter = iter.next;
            ++counter;
        }
        return counter;
    }

    public boolean add(Object o){
        ListNode newNode = new ListNode(o);
        if (isEmpty()){
            header.next = newNode;
            header.prev = newNode;
            newNode.next = header;
            newNode.prev = header;
        } else {
            ListNode iterator = header;
            while (iterator.next != header){
                iterator = iterator.next;
            }
            iterator.next = newNode;
            newNode.prev = iterator.next;
            newNode.next = header;
            header.prev = newNode;
        }
        return true;
    }

    public boolean remove(Object o){
        if (isEmpty() || o == null) return false;
        ListNode iter = header;
        while (!iter.next.data.equals(o)){
            if(iter.next == header) return false; //not found
            iter = iter.next;
        }
        iter.next = iter.next.next; //skip deleted node
        iter.next.prev = iter; //skip deleted node (backwards)
        return true;
    }

    public boolean isEmpty(){
        return header.next == header;
    }

    public void clear(){
        header.next = header;
        header.prev = header;
    }

    class ListNode {
        Object data;
        ListNode next;
        ListNode prev;

        public ListNode(Object data) {
            this.data = data;
        }
    }
}
