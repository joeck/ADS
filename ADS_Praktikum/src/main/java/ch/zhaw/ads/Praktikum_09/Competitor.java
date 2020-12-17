package ch.zhaw.ads.Praktikum_09;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Competitor implements Comparable<Competitor> {
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor(int startNr, String name, int jg, String country, String time) throws ParseException {
        this.startNr = startNr;
        this.name = name;
        this.jg = jg;
        this.country = country;
        this.time = parseTime(time);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append("Rang: ");
        sb.append(rank);
        sb.append(" Start Nr: ");
        sb.append(startNr);
        sb.append(" Name: ");
        sb.append(name);
        sb.append(" JG: ");
        sb.append(Integer.toString(jg));
        sb.append(" Zeit: ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    @Override
    public int compareTo(Competitor toCompare) {
        int c = name.compareTo(toCompare.name);
        c = (c != 0) ? c : jg - toCompare.jg;
        return c;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + name.hashCode();
        hash = hash * 17 + jg;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitor that = (Competitor) o;
        return time == that.time && jg == that.jg && startNr == that.startNr && rank == that.rank && name.equals(that.name) && country.equals(that.country);
    }
}
