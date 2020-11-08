import java.util.*;

public class RouteServer implements CommandExecutor {

    private Graph<DijkstraNode<Edge<?>>, Edge<DijkstraNode<?>>> graph;

    private void add(String one, String two, double weight) {
        try {
            if (graph.findNode(one) == null) {
                graph.addNode(one);
            }
            if (graph.findNode(two) == null) {
                graph.addNode(two);
            }
            graph.addEdge(one, two, weight);
            graph.addEdge(two, one, weight);
        } catch (Throwable ignored) {
            //dummy
        }
    }

    private void dijkstra(String from, String to) {
        Queue<DijkstraNode<Edge<?>>> redNodes = new PriorityQueue<>();

        DijkstraNode<Edge<?>> start = graph.findNode(from);
        DijkstraNode<Edge<?>> goal = graph.findNode(to);
        start.setDist(0);
        redNodes.add(start);

        while (!redNodes.isEmpty()) {
            DijkstraNode<Edge<?>> curr = redNodes.remove();
            if (curr.compareTo(goal) == 0) {
                return; //destination reached
            }
            curr.setMark(true);
            for (Edge<?> edge : curr.getEdges()) {
                DijkstraNode<Edge<?>> nextNode = (DijkstraNode<Edge<?>>) edge.getDest();
                if (!nextNode.getMark()) {
                    if (!redNodes.contains(nextNode)) {
                        redNodes.add(nextNode);
                    }
                    double dist = curr.dist + edge.getWeight();

                    if (dist < nextNode.dist) {
                        nextNode.setDist(dist);
                        nextNode.setPrev(curr);
                    }
                }
            }
        }

    }

    private String runDijkstra() {

        //init
        for (DijkstraNode<Edge<?>> curr : graph.getNodes()) {
            curr.setMark(false);
            curr.setDist(Double.MAX_VALUE);
            curr.setPrev(null);
        }

        String start = "Winterthur";
        String goal = "Lugano";
        dijkstra(start, goal);

        DijkstraNode<Edge<?>> current = graph.findNode(goal);
        List<String> res = new ArrayList<>();
        res.add(current.getName());
        while ((current = current.getPrev()) != null) {
            res.add(current.getName());
        }
        Collections.reverse(res);
        return String.join("<-", res);
    }

    private String run(String[] distance) {
        graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
        for (String line : distance) {
            String[] con = line.split(" ");
            double weight = Double.parseDouble(con[2]);
            String src = con[0];
            String dest = con[1];
            add(src, dest, weight);
        }

        return runDijkstra();
    }

    @Override
    public String execute(String command) throws Exception {
        return run(command.split("\\r?\\n"));
    }

}