package ch.zhaw.ads.Praktikum_06;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.Praktikum_03_Loesung.Competitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.*;

public class RankingTreeServer implements CommandExecutor {

    private final AVLSearchTree<Competitor> competitors;

    public RankingTreeServer() {
        this.competitors = new AVLSearchTree<>();
    }

    public AVLSearchTree<Competitor> getCompetitors() {
        return competitors;
    }

    @Override
    public String execute(String command) throws Exception {
        return (insertCompetitor(command)) ? getOutputData() : "List fucked up";
    }

    public boolean insertCompetitor(String rangliste) throws Exception {
        String[] lines = rangliste.split("\n");
        Iterator<String> iterator = Arrays.stream(lines).iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] elements = line.split(";");
            competitors.add(new Competitor(Integer.parseInt(elements[0]),
                    elements[1],
                    Integer.parseInt(elements[2]),
                    elements[3],
                    elements[4]));
        }
        return true;
    }

    String openFile()  throws Exception {
        String rangZuerich = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/main/resources/ch.zhaw.ads/Praktikum_06/RangZuerich.csv";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(rangZuerich), StandardCharsets.ISO_8859_1));
        StringBuffer b = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            b.append(line);
            b.append('\n');
        }
        return b.toString();
    }

    public String getOutputData() {
        Visitor<Competitor> v = new MyVisitor<>();
        competitors.traversal().postorder(v);
        return v.toString();
    }


    public int compare(Competitor o1, Competitor o2) {
        if (o1.getName().compareTo(o2.getName()) == 0) {
            return Integer.compare(o1.getJg(), o2.getJg());
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }

    static class MyVisitor<T extends Comparable<T>> implements Visitor<T> {
        StringBuilder output;

        MyVisitor() {
            output = new StringBuilder();
            output.append("Nr Name, Vorname  JG   Wohnort   Zeit\n");
        }

        public void visit(T s) {
            output.append(s);
            output.append("\n");
        }

        public String toString() {
            return output.toString();
        }
    }
}
