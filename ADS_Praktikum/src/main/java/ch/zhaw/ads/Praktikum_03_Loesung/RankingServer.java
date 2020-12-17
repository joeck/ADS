package ch.zhaw.ads.Praktikum_03_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;

public class RankingServer implements CommandExecutor {
    private static final String SEPARATING_CHAR = ";";

    @Override
    public String execute(String command) throws Exception {
        List<Competitor> competitorList = new LinkedList<>();

        String[] lines = command.split("\n");
        for (String line : lines) {
            String[] split = line.split(SEPARATING_CHAR);
            int startNr = Integer.parseInt(split[0]);
            String name = split[1];
            int jahrgang = Integer.parseInt(split[2]);
            String country = split[3];
            String time = split[4];
            competitorList.add(new Competitor(startNr, name, jahrgang, country, time));
        }

        Collections.sort(competitorList);
        int rank = 1;
        for (Competitor c : competitorList) {
            c.setRank(rank);
            rank++;
        }
        StringBuilder sb = new StringBuilder();
        for (Competitor c : competitorList) {
            sb.append(c+"\n");
        }

        sb.append("-----------------------------------\n");
        Collections.sort(competitorList, new AlphaComparer());
        for (Competitor c : competitorList) {
            sb.append(c+"\n");
        }

        return sb.toString();
    }
}

class AlphaComparer implements Comparator<Competitor> {
    @Override
    public int compare(Competitor o1, Competitor o2) {
        int c = o1.getName().compareTo(o2.getName());
        c = (c == 0)? o1.getJg() - o2.getJg(): c;
        return c;
    }
}