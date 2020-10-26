public class MyRankingVisitor implements Visitor<Competitor> {
    private StringBuilder buf;
    private int rank=1;

    public MyRankingVisitor(StringBuilder buf) {
        this.buf = buf;
    }

    public void visit(Competitor o) {
        o.setRank(rank++);
        buf.append(o.toString()+'\n');
    }
}
