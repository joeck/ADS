package ch.zhaw.ads.Praktikum_05_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;
import java.text.*;

public class RankingTreeServer implements CommandExecutor {

    private final static int STARTNR = 0;
    private final static int NAME = 1;
    private final static int JG = 2;
    private final static int COUNTRY = 3;
    private final static int TIME = 4;
    Tree<Competitor> tree = new SortedBinaryTree<Competitor>();

    public void load(Tree<Competitor> tree, String list) throws Exception {
        String[] lines = list.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] items = lines[i].split(";");
            Competitor c = new Competitor(Integer.parseInt(items[STARTNR]), // startNr
                    items[NAME], // name
                    Integer.parseInt(items[JG]), // jg
                    items[COUNTRY], // country
                    items[TIME]); // time
            tree.add(c);
        }
    }

    public String execute(String command) throws Exception {
        // list of all Competitors

        //read values from String
        load(tree,command);
        StringBuilder buf = new StringBuilder();
        Traversal<Competitor> trav = tree.traversal();
        Visitor<Competitor> vis = new MyRankingVisitor(buf);
        trav.inorder(vis);
        return buf.toString();
    }
}
