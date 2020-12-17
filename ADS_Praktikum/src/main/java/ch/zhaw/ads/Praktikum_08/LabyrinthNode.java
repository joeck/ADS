package ch.zhaw.ads.Praktikum_08;

public class LabyrinthNode<E> extends Node<E> {
    private LabyrinthNode<E> prev;

    private boolean mark = false;

    public LabyrinthNode() {
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean getMark() {
        return mark;
    }
    public LabyrinthNode<E> getPrev() {
        return prev;
    }

    public void setPrev(LabyrinthNode<E> prev) {
        this.prev = prev;
    }
}
