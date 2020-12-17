package ch.zhaw.ads.Praktikum_06;

import java.util.*;

public class SortedBinaryTree<T extends Comparable<T>> implements Tree<T> {
    protected TreeNode<T> root;

    private TreeNode<T> insertAt(TreeNode<T> node, T x) {
        if (node == null) {
            return new TreeNode<T>(x);
        } else {
            if (x.compareTo(node.element) <= 0) {
                node.left = insertAt(node.left, x);
            } else {
                node.right = insertAt(node.right, x);
            }
            return node;
        }
    }

    public void add(T x) {
        root = insertAt(root, x);
    }

    // find node to replace
    private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
        if (node.right != null) {
            node.right = findRepAt(node.right,rep);
        } else {
            rep.element = node.element;
            node = node.left;
        }
        return node;
    }

    // remove node
    private TreeNode<T> removeAt(TreeNode<T> node, T x, TreeNode<T> removed ) {
        if (node == null) {
            return null;
        } else {
            if (x.compareTo(node.element) == 0) {
                // found
                removed.element = node.element;
                if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    node.left = findRepAt(node.left,node);
                }
            } else if (x.compareTo(node.element) < 0) {
                // search left
                node.left = removeAt(node.left, x, removed);
            } else {
                // search right
                node.right = removeAt(node.right, x, removed);
            }
            return node;
        }
    }

    public T remove(T x) {
        TreeNode<T> removed = new TreeNode<T>(null);
        root = removeAt(root, x, removed);
        return removed.element;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public Traversal<T> traversal() {
        return new TreeTraversal<T>(root);
    }

    protected int calcHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(calcHeight(node.left),calcHeight(node.right));
        }
    }

    public int height() {
        return calcHeight(root);
    }

    protected int calcSize(TreeNode p) {
        if (p == null) {
            return 0;
        } else {
            return p.count + calcSize(p.left) + calcSize(p.right);
        }
    }

    public int size() {
        return calcSize(root);
    }

    private boolean balanced(TreeNode<T> node) {
        if (node == null) {
            return true;
        } else {
            return Math.abs(calcHeight(node.left) - calcHeight(node.right)) < 2
                    && balanced(node.left) && balanced(node.right);
        }
    }

    public boolean balanced() {
        return balanced(root);
    }

    // only for testing and debugging: show the structure of the tree
    public String printTree() {
        StringBuilder out = new StringBuilder();
        if (root.right != null) {
            printTree(root.right,out, true, "");
        }
        out.append(root.element+"\n");
        if (root.left != null) {
            printTree(root.left,out, false, "");
        }
        return out.toString();
    }

    private void printTree(TreeNode node, StringBuilder out, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, out, true,
                    indent + (isRight ? "        " : " |      "));
        }
        out.append(indent);
        if (isRight) {
            out.append(" /");
        } else {
            out.append(" \\");
        }
        out.append("----- ");
        out.append(node.element+"\n");
        if (node.left != null) {
            printTree(node.left, out, false,
                    indent + (isRight ? " |      " : "        "));
        }
    }

    public TreeNode<T> getRoot() {
        return root;
    }
}