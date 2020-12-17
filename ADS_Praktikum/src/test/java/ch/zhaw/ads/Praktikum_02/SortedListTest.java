package ch.zhaw.ads.Praktikum_02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedListTest {
    SortedList<String> sortedList;

    @Before
    public void setUp() throws Exception {
        sortedList = new SortedList<>();
    }

    @Test
    public void testCompare() {
        ListNode<String> listNode1 = new ListNode<>("1");
        ListNode<String> listNode2 = new ListNode<>("3");
        assertEquals("-1", listNode1.compareTo(listNode2));
        assertEquals("0", listNode2.compareTo(listNode2));
        assertEquals("1", listNode2.compareTo(listNode1));

        sortedList.add("1");
        sortedList.add("3");
        sortedList.add("4");
        sortedList.add("5");
        sortedList.add("2");
        assertEquals("1", sortedList.get(0));
        assertEquals("2", sortedList.get(1));
        assertEquals("3", sortedList.get(2));
        assertEquals("4", sortedList.get(3));
        assertEquals("5", sortedList.get(4));
    }
}
