import java.util.*;

public class LabyrinthNode<E> {
    protected String name; // Name
    protected List<E> edges; // Kanten
    private int x;
    private int y;
    boolean marked;

    public LabyrinthNode() {
        edges = new LinkedList<E>();
    }

    public LabyrinthNode(String name) {
        super();
        this.name = name;
        setCoordinates(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setCoordinates(name);
    }

    public Iterable<E> getEdges() {
        return edges;
    }

    public void addEdge(E edge) {
        edges.add(edge);
    }

    private void setCoordinates(String name){
        String[] coordinates = name.split("-");
        setX(Integer.parseInt(coordinates[0]));
        setY(Integer.parseInt(coordinates[1]));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
