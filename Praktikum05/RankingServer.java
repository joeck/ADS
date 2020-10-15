
import static java.lang.Integer.parseInt;

public class RankingServer implements CommandExecutor {
    private SortedBinaryTree<Competitor> tree = new SortedBinaryTree<>();

    private void readCSV(String csv){
        String[] lines = csv.split("\n");
        for (String line : lines){
            String[] attributes = line.split(";");
            tree.add(new Competitor(
                    parseInt(attributes[0]),
                    attributes[1],
                    parseInt(attributes[2]),
                    attributes[3],
                    attributes[4]
            ));
        }
    }

    private void calculateRanks(){
        RankingVisitor v = new RankingVisitor();
        tree.traversal().inorder(v);
    }

    private String printList(){
        Visitor<Competitor> v = new MyVisitor<>();
        tree.traversal().inorder(v);
        return v.toString();
    }

    @Override
    public String execute(String command) throws Exception {
        readCSV(command);
        calculateRanks();
        return printList();
    }

    class RankingVisitor implements Visitor<Competitor> {

        int counter = 1;

        public void visit(Competitor s) {
           s.setRank(counter++);
        }
    }
}
