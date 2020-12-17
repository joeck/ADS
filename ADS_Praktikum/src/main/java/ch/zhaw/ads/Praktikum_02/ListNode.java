package ch.zhaw.ads.Praktikum_02;

public class ListNode<U extends Comparable<U>> implements Comparable<ListNode<U>> {
    U data;
    ListNode<U> next, prev;

    ListNode() {}

    ListNode(U o) {
        data = o;
        next = null;
        prev = null;
    }

    public void setNextNode(ListNode<U> next) {
        this.next = next;
    }

    public void setPrevNode(ListNode<U> prev) {
        this.prev = prev;
    }

    public ListNode<U> getNextNode() {
        return this.next;
    }

    public ListNode<U> getPrevNode() {
        return this.prev;
    }

    public U getData() {
        return data;
    }

    public void setData(U data) {
        this.data = data;
    }

    @Override
    public int compareTo(ListNode<U> o) {
        if(o == null) {
            throw new NullPointerException("Comparable may not be a null");
        }
        // using the java compare method
        return data.compareTo(o.getData());
    }
}
