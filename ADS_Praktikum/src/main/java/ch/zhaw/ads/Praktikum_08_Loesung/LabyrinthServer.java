package ch.zhaw.ads.Praktikum_08_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;
import java.awt.*;

public class LabyrinthServer implements CommandExecutor {
    ServerGraphics g = new ServerGraphics();

    final double SCALE = 10;

    private void drawPath(String from, String to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) g.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                g.fillRect(x0,y0,x1-x0+w,w);
            else
                g.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    private boolean search(DijkstraNode<Edge> current, DijkstraNode<Edge> ziel) {
        current.setMark(true);
        System.out.println(current.getName());
        if (current == ziel) return true;
        for (Edge<DijkstraNode> e : current.getEdges()) {
            DijkstraNode<Edge> n = e.getDest();
            if (!n.getMark()) {
                n.setPrev(current);
                if (search(n,ziel)) return true;
            }
        }
        current.setMark(false);
        return false;
    }

    public String execute(String s) {
        Graph<DijkstraNode,Edge> graph
                = new AdjListGraph<DijkstraNode,Edge>(DijkstraNode.class,Edge.class);
        //read values from String
        StringTokenizer tok = new StringTokenizer(s);
        g.setColor(Color.gray);
        g.fillRect(0,0,1,1);
        g.setColor(Color.white);
        while (tok.hasMoreTokens()) {
            String from = tok.nextToken();
            String to = tok.nextToken();
            try {
                graph.addEdge(from, to, 1);
                graph.addEdge(to, from, 1);
                drawPath(from, to, false);
            }
            catch (Throwable e){};
        }

        DijkstraNode<Edge> start = graph.findNode("0-6");
        DijkstraNode<Edge> ziel = graph.findNode("3-0");

        // search and draw result
        if (search(start,ziel)) {
            g.setColor(Color.red);
            do {
                drawPath(ziel.getName(), ziel.getPrev().getName(),true);
                ziel = ziel.getPrev();
            }
            while (ziel.getPrev() != null);
        }
        return g.getTrace();
    }

}