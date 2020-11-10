import java.awt.*;

public class LabyrinthServer implements CommandExecutor {

    final double SCALE = 10;
    AdjListGraph<LabyrinthNode<String>, Edge<LabyrinthNode<String>>> labyrinth = new AdjListGraph<>(LabyrinthNode.class, Edge.class);
    GraphicsServer graphicsServer = new GraphicsServer();

    private void drawPath(GraphicsServer g, String from, String to, boolean mouse) {
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

    private void setup(){
        graphicsServer.setColor(Color.BLACK);
        graphicsServer.fillRect(0,0,10,7);
        graphicsServer.setColor(Color.WHITE);
    }

    private void addToGraph(String a, String b){
        try {
            labyrinth.addEdge(labyrinth.addNode(a).getName(), labyrinth.addNode(b).getName(), 1.0);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void process(String command){
        String[] lines = command.split("\n");
        for(String line : lines){
            String[] nodes = line.split(" ");
            try {
                addToGraph(nodes[0], nodes[1]);
                drawPath(graphicsServer, nodes[0], nodes[1], false);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public String execute(String command) throws Exception {
        setup();
        process(command);
        return graphicsServer.getTrace();
    }
}
