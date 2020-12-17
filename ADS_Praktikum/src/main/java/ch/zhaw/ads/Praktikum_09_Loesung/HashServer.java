package ch.zhaw.ads.Praktikum_09_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;
import java.text.*;

public class HashServer implements CommandExecutor {
    private final static int STARTNR = 0;
    private final static int NAME = 1;
    private final static int JG = 2;
    private final static int COUNTRY = 3;
    private final static int TIME = 4;

    private Map<Competitor,Competitor> data = new MyHashtable<>(4000);

    private long parseTime(String s) throws Exception {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public void load(Map data, String list) throws Exception {
        String[] lines = list.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] items = lines[i].split(";");
            Competitor c = new Competitor(Integer.parseInt(items[STARTNR]), // startNr
                    items[NAME], // name
                    Integer.parseInt(items[JG]), // jg
                    items[COUNTRY], // country
                    items[TIME]); // time
            data.put(c,c);
        }
    }

    public String execute(String arg) throws Exception {
        if (arg.toUpperCase().startsWith("GET")) {
            String[] items = arg.substring(3).trim().split(";");
            Competitor key = new Competitor(0,items[0],Integer.parseInt(items[1]),"","00:00:00.0");
            Competitor o = data.get(key);
            if (o != null) {
                return items[0] + " " + items[1] + " -> " + o.toString() + "\n";
            }
            else {
                return "not found\n";
            }
        }
        else {
            load(data,arg);
            return ""+data.size() + " loaded\n";
        }
    }

}