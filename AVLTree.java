import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * 
 * Realizar rotação para o caso direita, direita: A,B,C.
 * 
 */
class Node {
    Character data;
    Node left;
    Node right;
    Node parent;
    int height;

    Node(Character data) {
        this.data = data;
        left = right = null;
        height = 0;
    }

    public int fb() {
        int f = (right==null?-1:right.height) - (left==null?-1:left.height);
        return f;
    }

    @Override
    public String toString() {
        return String.format("(%s data=%c h=%d fb=%d %s)",
                left == null ? "#" : left.toString(),
                data,
                height,
                fb(),
                right == null ? "#" : right.toString());
    }

    public String pre() {
        return String.format("%c %s %s",
                data,
                left == null ? "" : left.pre(),
                right == null ? "" : right.pre());
    }

    public String central() {
        if (left == null && right == null)
            return String.format("%c",
                    data);
        if (parent == null)
            return String.format("%s %c %s",
                    left == null ? "" : left.central(),
                    data,
                    right == null ? "" : right.central());
        return String.format("(%s %c %s)",
                left == null ? "" : left.central(),
                data,
                right == null ? "" : right.central());
    }

    public String pos() {
        return String.format("%S %s %c",
                left == null ? "" : left.pos(),
                right == null ? "" : right.pos(),
                data);
    }

    public List<Character> breadth() { // width?
        Queue<Node> q = new LinkedList<>();
        List<Character> result = new ArrayList<>();
        q.add(this);
        while (!q.isEmpty()) {
            Node n = q.remove();
            result.add(n.data);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
        return result;
    }
}

public class AVLTree {

    private Node root;

    public AVLTree() {
        root = null;
    }

    public int height() {
        return height(root);
    }

    // O(n)
    private int height(Node node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(Character c) {
        root = add(root, c);
    }

    private Node add(Node node, Character c) {
        if (node == null)
            return new Node(c);
        if (node.data < c)
            node.right = add(node.right, c);
        else
            node.left = add(node.left, c);
        node.height = 1 + Math.max(h(node.left), h(node.right));
        return node;
    }

    private int h(Node node) {
        if (node == null)
            return -1;
        return node.height;
    }

    public String toString() {
        if (isEmpty())
            return "#";
        return root.toString();
    }

    public static void example3() {
        AVLTree t = new AVLTree();

        System.out.println(t);
        System.out.println(t.isEmpty());
        System.out.println(t.height());

        t.add('A');

        System.out.println(t);
        System.out.println(t.isEmpty());
        System.out.println(t.height());

        t.add('B');

        System.out.println(t);
        System.out.println(t.isEmpty());
        System.out.println(t.height());

        t.add('C');

        System.out.println(t);
        System.out.println(t.isEmpty());
        System.out.println(t.height());
    }

    public static void main(String[] args) {
        example3();
    }
}