package ch.zhaw.ads.Praktikum_03;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;

public class RankingServer implements CommandExecutor {
    private final List<Competitor> competitor = new ArrayList<>();

    @Override
    public String execute(String command) throws Exception {
        if (insertCompetitor(command)) {
            sortRank();
            sortName();
            return getOutputData().toString();
        } else {
            return "Not worked";
        }
    }

    public StringBuilder getOutputData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nr Name,Vorname  JG   Wohnort   Zeit\n");
        for (Competitor competitor: this.competitor) {
            stringBuilder.append(competitor.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    public void sortRank() {
        Collections.sort(competitor);
        for (int rank = 0; rank < competitor.size(); rank++) {
            Competitor competitor_ = competitor.get(rank);
            competitor_.setRank(rank + 1);
        }
    }

    // Namelist
    public void sortName() {
        NamelistCompare namelistCompare = new NamelistCompare();
        Collections.sort(competitor, namelistCompare);
    }

    public boolean insertCompetitor(String args) {
        if (args.isBlank()) return false;
        String[] lines = args.split("\n");
        Iterator<String> iterator = Arrays.stream(lines).iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] elements = line.split(";");
            competitor.add(new Competitor(Integer.parseInt(elements[0]),
                    elements[1],
                    Integer.parseInt(elements[2]),
                    elements[3],
                    elements[4]));
        }
        return true;
    }
}
