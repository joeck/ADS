package ch.zhaw.ads.Praktikum_02_Loesung;

import java.util.*;

public class MySortedList extends MyList {

    @Override
    public boolean add(Object val){
        ListNode p = head;
        while(p != HEADER && p.value.compareTo(val) < 0){
            p = p.next;
        }
        insertBefore(val,p);
        return true;
    }

}
