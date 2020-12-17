package ch.zhaw.ads.Praktikum_02_Loesung;

import java.util.*;

class ListNode {
    Comparable value;
    ListNode next, prev;

    ListNode(Object value) {
        this.value = (Comparable) value;
    }
}

public class MyList extends AbstractList    {
    protected int size;
    protected ListNode head;
    protected final ListNode HEADER; // dummy Element

    protected void insertBefore(Object val, ListNode p) {
        ListNode n = new ListNode(val);
        n.next = p;
        n.prev = p.prev;
        p.prev.next = n;
        p.prev = n;
        size++;
    }

    protected void remove(ListNode p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
    }
    // ============== public ===================
    public MyList() {
        HEADER = new ListNode(null);
        clear();
    }

    @Override
    public void clear() {
        HEADER.next = HEADER;
        HEADER.prev = HEADER;
        head = HEADER;
        size = 0;
    }

    @Override
    public boolean add(Object val) {
        insertBefore(val, head);
        return true;
    }

    @Override
    public boolean remove(Object val) {
        for (ListNode p = head.next; p != HEADER; p = p.next) {
            if (p.value.equals(val)) {
                remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode p = head.next;
        for (;pos > 0; pos--) {
            p = p.next;
        }
        return p.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
