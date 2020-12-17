package ch.zhaw.ads.Praktikum_02;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

public class ListTest {

    List<String> list;

    @Before
    public void setUp() throws Exception {
        list = new MyList<>();
    }

    @Test
    public void testAdd() {
        list.clear();
        list.add("A");
        Object o = list.get(0);
        assertEquals(o, "A");
    }

    @Test
    public void testAdd2() {
        list.clear();
        list.add("A");
        list.add("B");
        Object o = list.get(0);
        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "B");
    }

    @Test
    public void testAdd3() {
        list.clear();
        list.add("A");
        list.add("B");
        list.add("C");
        Object o = list.get(0);
        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "B");
        o = list.get(2);
        assertEquals(o, "C");
    }

    @Test
    public void testSize() {
        list.clear();
        assertEquals(0, list.size());
        testAdd2();
        assertEquals(2, list.size());
    }

    @Test
    public void testRemove() {
        list.clear();
        list.add("A");
        list.remove("A");
        assertEquals(0, list.size());
        list.add("A");
        list.remove("B");
        assertEquals(1, list.size());
        list.remove("A");
        assertEquals(0, list.size());
    }

    @Test
    public void testMixed() {
        MyList<Character> list1 = new MyList<>();
        List<Character> list2 = new LinkedList<Character>();
        for (int i = 0; i < 100; i++) {
            Character c = (char) ('A' + (Math.random()*26));
            int op = (int)(Math.random()*2);
            switch (op) {
                case 0 :
                    list1.add(c);
                    list2.add(c);
                    break;
                case 1 :
                    list1.remove(c);
                    list2.remove(c);
                    break;
            }
        }
        assertEquals(list2.size(), list1.size());
        for (int i = 0; i < list1.size(); i++) {
            char c1 = (char)list1.get(i);
            char c2 = (char)list2.get(i);
            assertEquals(c1,c2);
        }
    }
}
