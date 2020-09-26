package Praktikum_02_Code;

public class MySortedList extends MyList {

    @Override
    public boolean add(Object o) {
        ListNode toInsert = new ListNode(o);
        ListNode iter = header;
        while (toInsert.compareTo(iter.next) >= 0){
            iter = iter.next;
        }
        insertBefore(new ListNode(o), iter.next);
        return true;
    }
}
