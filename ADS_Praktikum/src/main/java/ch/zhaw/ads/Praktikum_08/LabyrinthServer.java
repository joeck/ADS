package ch.zhaw.ads.Praktikum_08;

import ch.zhaw.ads.CommandExecutor;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;

public class LabyrinthServer implements CommandExecutor {


    private Graph<LabyrinthNode,Edge> graph;
    private final ServerGraphics serverGraphics;
    private LabyrinthNode<Edge> goal;

    public LabyrinthServer() {
        this.graph = new AdjListGraph<>(LabyrinthNode.class, Edge.class);
        this.serverGraphics = new ServerGraphics();
    }

    @Override
    public String execute(String command) throws Throwable {
        insert(openFile());
        paintBlack();
        printLabyrinth();
        goal = graph.findNode("3-0");
        LabyrinthNode<Edge> start = graph.findNode("0-6");
        serverGraphics.setColor(Color.RED);
        search(start);
        return serverGraphics.getTrace();
    }

    private void paintBlack() {
        serverGraphics.setColor(Color.BLACK);
        serverGraphics.drawRect(0,0,1,1);
        serverGraphics.fillRect(0,0,1,1);
    }

    private void printLabyrinth() {
        Iterable<LabyrinthNode> nodes = graph.getNodes();
        for(LabyrinthNode lNode : nodes) {
            Iterable iter = lNode.getEdges();
            for(Object e : iter) {
                Edge edge = (Edge) e;
                serverGraphics.setColor(Color.WHITE);
                LabyrinthNode neighbour = (LabyrinthNode) edge.getDest();
                drawPath(lNode.getName(), neighbour.getName(), false);
            }
        }
    }

    public boolean insert(String routes) throws Throwable {
        String[] lines = routes.split("\n");
        Iterator<String> iterator = Arrays.stream(lines).iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] elements = line.split(" ");
            graph.addEdge(elements[0], elements[1], 1);
            graph.addEdge(elements[1], elements[0], 1);
        }
        return true;
    }

    String openFile()  throws Exception {
        String labyrinth = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/main/java/ch/zhaw/ads/Praktikum_08/Labyrinth.txt";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(labyrinth), StandardCharsets.UTF_8));
        StringBuffer b = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            b.append(line);
            b.append('\n');
        }
        return b.toString();
    }

    private void drawPath(String from, String to, boolean mouse) {
        final double SCALE = 10;
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) serverGraphics.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                serverGraphics.fillRect(x0,y0,x1-x0+w,w);
            else
                serverGraphics.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    private boolean search(LabyrinthNode<Edge> currentNode) {
        currentNode.setMark(true);
        if(currentNode == goal) return true;

        for (Edge edge : currentNode.edges) {
            LabyrinthNode<Edge> node = (LabyrinthNode<Edge>) edge.getDest();
            if (!node.getMark()) {
                if (search(node)) {
                    System.out.println(currentNode.name + " " + node.name);
                    drawPath(node.name, currentNode.name, true);
                    return true;
                }
            }
        }
        currentNode.setMark(false);
        return false;
    }
}