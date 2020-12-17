package ch.zhaw.ads.Praktikum_05;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
        inorderInside(root, vis);
    }

    private void inorderInside(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            inorderInside(node.left, vis);
            vis.visit(node.element);
            inorderInside(node.right, vis);
        }
    }

    public void preorder(Visitor<T> vis) {
        preorderInside(root, vis);
    }

    public void preorderInside(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            vis.visit(node.element);
            preorderInside(node.left, vis);
            preorderInside(node.right, vis);
        }
    }


    public void postorder(Visitor<T> vis) {
        postorderInside(root, vis);
    }

    public void postorderInside(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            postorderInside(node.left, vis);
            postorderInside(node.right, vis);
            vis.visit(node.element);
        }
    }

    @Override
    public void levelorder(Visitor<T> visitor) {
        levelorderInside(root, visitor);
    }

    public void levelorderInside(TreeNode<T> node, Visitor<T> vis) {
        if (node == null) return;
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()){
            node = q.remove();
            vis.visit(node.element);
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
    }

    @Override
    public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> v) {
        intervalInside(root, min, max, v);
    }

    public void intervalInside(TreeNode<T> node, Comparable<T> min, Comparable<T> max, Visitor<T> v) {
        if (node != null) {
            if (min.compareTo(node.element) <= 0 && max.compareTo(node.element) >= 0) {
                v.visit(node.element);
            }
            intervalInside(node.left, min, max, v);
            intervalInside(node.right, min, max, v);
        }
    }
}
