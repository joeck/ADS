package ch.zhaw.ads.Praktikum_07_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;

public class RouteServer implements CommandExecutor {

    Graph<DijkstraNode, Edge> graph = new  AdjListGraph<DijkstraNode, Edge>(DijkstraNode.class,Edge.class);

    private void dijkstra(String from, String to) {
        java.util.Queue<DijkstraNode> pq = new java.util.PriorityQueue<DijkstraNode>();
        DijkstraNode<Edge> town = graph.findNode(from);
        DijkstraNode<Edge> goal = graph.findNode(to);
        town.setDist(0);
        town.setMark(true);
        pq.add(town);
        while (!pq.isEmpty()) {
            DijkstraNode<Edge> currentTown = pq.remove();
            System.out.println(
                    "dequeue:" + currentTown.getName() + " " + currentTown.getDist()
                            + "\n");
            currentTown.setMark(true);
            if (currentTown == goal) return;
            for (Edge road: currentTown.getEdges()) {
                town = (DijkstraNode) road.getDest();
                if (!town.getMark()) {
                    double dist = currentTown.getDist() + road.getWeight();
                    if ((town.getPrev() == null) || (dist < town.getDist())) {
                        town.setDist(dist);
                        town.setPrev(currentTown);
                        System.out.println(
                                "enqueue:" + town.getName() + " " + town.getDist());
                        pq.add(town);
                    }
                }
            }
        }
    }

    public String execute(String s) throws Exception {
        StringBuffer buf = new StringBuffer();
        graph = new  AdjListGraph<DijkstraNode, Edge>(DijkstraNode.class,
                Edge.class);
        // read values from String
        StringTokenizer tok = new StringTokenizer(s);
        while (tok.hasMoreTokens()) {
            String from = tok.nextToken();
            String to = tok.nextToken();
            int dist = Integer.parseInt(tok.nextToken());
            try {
                graph.addEdge(from, to, dist);
                graph.addEdge(to, from, dist);
            } catch (Throwable t) {
                throw new Exception(t);
            }

        }
        String start = "Winterthur";
        String goal = "Lugano";

        dijkstra(start, goal);

        // get result
        DijkstraNode<Edge> town = graph.findNode(goal);
        do {
            buf.append(town.getName() + " " + town.getDist() + '\n');
            town = town.getPrev();
        } while (town != null);
        return buf.toString();
    }
}