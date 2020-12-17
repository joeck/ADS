package ch.zhaw.ads.Praktikum_06;

/* interface of visitor ADT */
public interface Visitor<T extends Comparable<T>> {
    /* called for each element in the tree */
    public void visit(T obj);
}
