package ch.zhaw.ads.Praktikum_03;

import java.util.Comparator;

public class NamelistCompare implements Comparator<Competitor> {
    @Override
    public int compare(Competitor o1, Competitor o2) {
        if (o1.getName().compareTo(o2.getName()) == 0) {
            return Integer.compare(o1.getJg(), o2.getJg());
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
