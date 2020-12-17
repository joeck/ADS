package ch.zhaw.ads.Praktikum_02;

import java.util.*;

public class MyList<T extends Comparable<T>> extends AbstractList<T> {
    protected ListNode<T> head;
    protected int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean add(T o) {
        if (o != null) {
            if (head == null) {
                head = new ListNode<>(o);
                head.setNextNode(head);
                head.setPrevNode(head);
            } else {
                ListNode<T> newNode = new ListNode<T>(o);
                ListNode<T> lastNode = getLastNode();
                lastNode.setNextNode(newNode);
                newNode.setPrevNode(lastNode);
                newNode.setNextNode(head);
                head.setPrevNode(newNode);
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    public ListNode<T> getLastNode() {
        ListNode<T> listNode = head;
        while(listNode.getNextNode() != head){
            listNode = listNode.getNextNode();
        }
        return listNode;
    }

    public boolean remove(Object o) {
        ListNode<T> listNode = head;
        ListNode<T> searchNode = new ListNode<>();
        searchNode.setData((T) o);
        if (o == null || head == null) return false;
        boolean stop = false;
        do {
            if(listNode.compareTo(searchNode) == 0) {
                if (size == 1) {
                    clear();
                } else if (head.compareTo(searchNode) == 0) {
                    ListNode<T> nextNode = listNode.getNextNode();
                    ListNode<T> prevNode = listNode.getPrevNode();
                    head = nextNode;
                    head.setPrevNode(prevNode);
                    prevNode.setNextNode(head);
                    size--;
                } else {
                    ListNode<T> nextNode = listNode.getNextNode();
                    ListNode<T> prevNode = listNode.getPrevNode();
                    prevNode.setNextNode(nextNode);
                    nextNode.setPrevNode(prevNode);
                    size--;
                }
                stop = true;
            }
            listNode = listNode.getNextNode();
        } while(listNode != head && !stop);

        return true;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T get (int pos) {
        ListNode<T> node = this.head;
        while (pos > 0) {
            node = node.next;
            pos--;
        }
        return node.data;
    }
}
