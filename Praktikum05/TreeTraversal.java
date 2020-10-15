import java.util.LinkedList;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

	@Override
    public void inorder(Visitor<T> vis) {
        inOrder(root, vis);
    }

	@Override
    public void preorder(Visitor<T> vis) {
        preOrder(root, vis);
    }

	@Override
    public void postorder(Visitor<T> vis) {
        postOrder(root, vis);
    }

    public void levelorder(Visitor<T> vis){
        levelOrder(root, vis);
    }

	@Override
    public void interval(T min, T max, Visitor<T> v) {
        interval(min, max, root, v);
    }

    private void interval(T min, T max, TreeNode<T> node, Visitor<T> vis){
        if(node != null){
            boolean isBigEnough = node.getValue().compareTo(min) > -1;
            boolean isSmallEnough = node.getValue().compareTo(max) < 1;
            if(isBigEnough) interval(min, max, node.left, vis);
            if(isBigEnough && isSmallEnough) vis.visit(node.getValue());
            if(isSmallEnough) interval(min, max, node.right, vis);
        }
    }

    private void inOrder(TreeNode<T> node, Visitor<T> vis){
        if(node != null){
            inOrder(node.left, vis);
            vis.visit(node.element);
            inOrder(node.right, vis);
        }
    }

    private void preOrder(TreeNode<T> node, Visitor<T> vis){
        if(node != null){
            vis.visit(node.element);
            preOrder(node.left, vis);
            preOrder(node.right, vis);
        }
    }

    private void postOrder(TreeNode<T> node, Visitor<T> vis){
        if(node != null){
            postOrder(node.left, vis);
            postOrder(node.right, vis);
            vis.visit(node.element);
        }
    }

    private void levelOrder(TreeNode<T> node, Visitor<T> vis){
        LinkedList<TreeNode<T>> list = new LinkedList<>();
        list.addLast(node);
        while(!list.isEmpty()){
            node = list.removeFirst();
            vis.visit(node.element);
            if (node.left != null) list.addLast(node.left);
            if (node.right != null) list.addLast(node.right);
        }
    }
}
