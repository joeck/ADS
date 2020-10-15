package Praktikum_03_Code;

import java.util.*;
import java.text.*;

public class Competitor implements Comparable<Competitor>{
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor(int startNr, String name, int jg, String country, String time) {
        this.startNr = startNr;
        this.name = name;
        this.jg = jg;
        this.country = country;
        try {
            this.time = parseTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        sb.append(rank);sb.append(" ");
        sb.append(name); sb.append(" ");
        sb.append(Integer.toString(jg)); sb.append(" ");
        sb.append(df.format(new Date(time)));
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo(Competitor competitor) {
        if(competitor.getTime() < this.getTime()){
            return +1;
        } else if(competitor.getTime() > this.getTime()){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;
        return this.toString().equals(((Competitor) obj).toString());
    }
}
