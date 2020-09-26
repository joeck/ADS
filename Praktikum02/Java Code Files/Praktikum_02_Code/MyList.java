package Praktikum_02_Code;

import javax.swing.text.html.ListView;
import java.util.AbstractList;

public class MyList extends AbstractList {

    protected final ListNode header = new ListNode(null);

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
        insertBefore(new ListNode(o), header);
        return true;
    }

    /**
     * Inserts new item b between a and c
     * a <-> c becomes a <-> b <-> c
     * @param b new item
     * @param c item after new item
     */
    public void insertBefore(ListNode b, ListNode c){
        c.prev.next = b; // a -> b
        b.prev = c.prev; // a <- b
        b.next = c; // b -> c
        c.prev = b; // b <- c
    }

    public boolean remove(Object o){
        if (isEmpty() || o == null) return false;
        ListNode iter = header;
        while (!o.equals(iter.next.data)){
            if(iter.next == header) return false; //not found
            iter = iter.next;
        }
        removeNode(iter.next);
        return true;
    }

    /**
     * Removes node b from a <-> b <-> c to a <-> c
     * @param b node to remove
     */
    protected void removeNode(ListNode b){
        b.prev.next = b.next; // a -> c
        b.next.prev = b.prev; // a <- c
    }

    public boolean isEmpty(){
        return header.next == header;
    }

    public void clear(){
        header.next = header;
        header.prev = header;
    }

    class ListNode implements Comparable<ListNode>{
        Object data;
        ListNode next;
        ListNode prev;

        public ListNode(Object data) {
            this.data = data;
        }

        @Override
        public int compareTo(ListNode listNode) {
            String s1 = this.data == null ? "" : String.valueOf(this.data);
            String s2 = String.valueOf(listNode.data);
            return s1.compareTo(s2);
        }


    }
}
