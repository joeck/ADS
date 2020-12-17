package ch.zhaw.ads.Praktikum_05;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.Praktikum_03.Competitor;
import ch.zhaw.ads.Praktikum_03.NamelistCompare;

import java.util.*;

public class RankingServer implements CommandExecutor {
    private final SortedBinaryTree<Competitor> competitor = new SortedBinaryTree<>();

    @Override
    public String execute(String command) throws Exception {
        if (insertCompetitor(command)) {
            return getOutputData();
        } else {
            return "Not worked";
        }
    }

    public String getOutputData() {
        Visitor<Competitor> v = new MyVisitor<>();
        competitor.traversal().postorder(v);
        return v.toString();
    }

    public SortedBinaryTree<Competitor> getCompetitors() {
        return competitor;
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


class MyVisitor<T extends Comparable<T>> implements Visitor<T> {
    StringBuilder output;

    MyVisitor() {
        output = new StringBuilder();
        output.append("Nr Name,Vorname  JG   Wohnort   Zeit\n");
    }

    public void visit(T s) {
        output.append(s);
        output.append("\n");
    }

    public String toString() {
        return output.toString();
    }
}
