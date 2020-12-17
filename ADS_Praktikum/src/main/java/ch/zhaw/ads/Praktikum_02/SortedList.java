package ch.zhaw.ads.Praktikum_02;

public class SortedList<T extends Comparable<T>> extends MyList<T> {
    @Override
    public boolean add(T o) {
        if (o != null) {
            if (head == null) {
                head = new ListNode<>(o);
                head.setNextNode(head);
                head.setPrevNode(head);
            } else {
                ListNode<T> lastNode = head;
                ListNode<T> newNode = new ListNode<>(o);
                boolean stop = false;
                while (lastNode.next.compareTo(newNode) < 0 && !stop) {
                    lastNode = lastNode.getNextNode();
                    stop = lastNode.getNextNode() == head;
                }
                ListNode<T> nextNode = lastNode.getNextNode();
                lastNode.setNextNode(newNode);
                newNode.setPrevNode(lastNode);
                newNode.setNextNode(nextNode);
                nextNode.setPrevNode(newNode);
            }
            size++;
            return true;
        } else {
            return false;
        }
    }
}
