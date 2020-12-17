package ch.zhaw.ads.Praktikum_09;

import ch.zhaw.ads.CommandExecutor;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class MyHashTableServer implements CommandExecutor {
    private final Map<String, Competitor> competitorMap;
    private final static int STARTNR = 0;
    private final static int NAME = 1;
    private final static int JG = 2;
    private final static int COUNTRY = 3;
    private final static int TIME = 4;

    public MyHashTableServer() {
        this.competitorMap = new MyHashtable<>(1);
    }

    /**
     * Example Insert: 1;Tim R;2000;Urdorf;03:01:01.1;
     *                  GET Tim R;2000
     *                  REMOVE Tim R;2000
     */
    @Override
    public String execute(String command) throws Exception, Throwable {
        if (command.toUpperCase().startsWith("GET")) {
            command = command.replace("GET ", "");
            String[] args = command.split(";");
            String name = args[0];
            String jg = args[1];
            return name + " " + jg + " -> " + competitorMap.get(name + jg).toString() + "\n";
        } else if (command.toUpperCase().startsWith("REMOVE")) {
            command = command.replace("REMOVE ", "");
            String[] args = command.split(";");
            String name = args[0];
            String jg = args[1];
            return "Element mit Key: " + name + " " + jg + " und Value: " + competitorMap.remove(name + jg).toString() + " wurde entfernt\n";
        } else {
            String[] lines = command.split("\n");
            if (lines.length == 1) {
                loadCompetitor(lines[0]);
                return "Inserted Competitor :)\n";
            } else if (lines.length == 0) {
                return "Nothing to load :(\n";
            } else {
                int countInserts = load(lines);
                return countInserts + " loaded :|\n";
            }
        }
    }

    private int load(String[] lines) throws ParseException {
        int countInserts = 0;
        for (String line : lines) {
            loadCompetitor(line);
            countInserts++;
        }
        return countInserts;
    }

    private void loadCompetitor(String line) throws ParseException {
        String[] items = line.split(";");
        Competitor c = new Competitor(Integer.parseInt(items[STARTNR]), // startNr
                items[NAME], // name
                Integer.parseInt(items[JG]), // jg
                items[COUNTRY], // country
                items[TIME]); // time
        competitorMap.put(items[NAME] + items[JG], c);
    }
}
