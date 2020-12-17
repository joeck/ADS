package ch.zhaw.ads.Praktikum_06_Loesung;

public class AVLSearchTree<T extends Comparable<T>> extends SortedBinaryTree<T> {

    T removed;

    private boolean balanced(TreeNode<T> node) {
        if (node == null) {
            return true;
        } else {
            return Math.abs(height(node.left) - height(node.right)) < 2
                    && balanced(node.left) && balanced(node.right);
        }
    }

    public boolean balanced() {
        return balanced(root);
    }

    public int height()
    {
        return root.height;
    }

    /**
     * Return the height of node t, or 0, if null.
     */
    private static int height(TreeNode t) {
        return t == null ? 0 : t.height;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param element the item to insert.
     */
    public void add(T element) {
        root = insertAt(root, element);
    }

    private TreeNode<T> balance(TreeNode<T> p) {
        if (p == null) {
            return null;
        } else if (height(p.left) - height(p.right) == 2) {
            if (height(p.left.left) > height(p.left.right)) {
                p = rotateR(p);
            } else {
                p = rotateLR(p);
            }
        } else if (height(p.right) - height(p.left) == 2) {
            if (height(p.right.right) > height(p.right.left)) {
                p = rotateL(p);
            } else {
                p = rotateRL(p);
            }
        }
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        return p;
    }

    /**
     * Internal method to insert into a subtree.
     * @param element the item to insert.
     * @param p the node that roots the tree.
     * @return the new root.
     */
    private TreeNode insertAt(TreeNode p, T element) {
        if (p == null) {
            p = new TreeNode<T>(element);
            p.height = 1;
            p.count = 1;
            return p;
        } else {
            int c = element.compareTo((T) p.element);
            if (c == 0) {
                p.count++;
            }
            else if (c < 0) {
                p.left = insertAt(p.left, element);
            } else if (c > 0) {
                p.right = insertAt(p.right, element);
            }
        }
        p = balance(p);
        return p;
    }

    // find node to replace
    private TreeNode<T> rep;
    private TreeNode<T> findRepAt(TreeNode<T> node) {
        if (node.right != null) {
            node.right = findRepAt(node.right);
            node = balance(node);
        } else {
            rep = node;
            node = node.left;
        }
        return node;
    }

    // remove node
    private TreeNode<T> removeAt(TreeNode<T> node, T x) {
        if (node == null) {
            return null;
        } else {
            if (x.compareTo(node.element) == 0) {
                // found
                removed = node.element;
                if (node.count > 1) {
                    node.count--;
                    return node;
                } else if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    node.left = findRepAt(node.left);
                    rep.left = node.left;
                    rep.right = node.right;
                    node = rep;
                }
            } else if (x.compareTo(node.element) <= 0) {
                // search left
                node.left = removeAt(node.left, x);
            } else {
                // search right
                node.right = removeAt(node.right, x);
            }
            node = balance(node);
            return node;
        }
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public T remove(T x) {
        removed = null;
        root = removeAt(root, x);
        return removed;
    }
    public Traversal<T> traversal() {
        return new AVLTreeTraversal<T>(root);
    }


    public T removeLast() {
        throw new UnsupportedOperationException();
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private static TreeNode rotateR(TreeNode k2) {
        TreeNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private static TreeNode rotateL(TreeNode k1) {
        TreeNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private static TreeNode rotateLR(TreeNode k3) {
        k3.left = rotateL(k3.left);
        return rotateR(k3);
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private static TreeNode rotateRL(TreeNode k1) {
        k1.right = rotateR(k1.right);
        return rotateL(k1);
    }

    public static void testMixed() {
        for (int i = 0; i < 200000; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
            int op = (int) (Math.random() * 2);
            switch (op) {
                case 0:
                    tree.add(c.toString());
                    break;
                case 1:
                    tree.remove(c.toString());
                    break;
            }
        }
        System.out.println(((SortedBinaryTree) tree).printTree());
        System.out.println("" + tree.balanced());
    }
    static Tree<String> tree = new AVLSearchTree<String>();
    public static void main(String[] s) {
        testMixed();
    }
}