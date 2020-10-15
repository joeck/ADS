public class CountingVisitor<T> implements Visitor<T> {

    private int counter = 0;

    @Override
    public void visit(T obj) {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
