import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * 
 * 
 */
class Node {
    Character data;
    Node left;
    Node right;
    Node parent;

    Node(Character data) {
        this.data = data;
        left = right = null;
    }

    @Override
    public String toString() {
        return String.format("(%s data=%c %s)",
                left == null ? "#" : left.toString(),
                data,
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

/**
 * 
 * BinarySearchTree
 */
public class BinarySearchTree {

    private Node root;

    // O(1)
    public BinarySearchTree() {
        root = null;
    }

    // O(n)
    public int height() {
        return height(root);
    }

    // O(n)
    private int height(final Node node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // O(1)
    public boolean isEmpty() {
        return root == null;
    }

    // O(n)
    public void add(Character c) {
        root = add(root, c);
    }

    // O(n)
    private Node add(Node node, Character c) {
        if (node == null)
            return new Node(c);
        if (node.data < c)
            node.right = add(node.right, c);
        else
            node.left = add(node.left, c);
        return node;
    }

    // O(n)
    public boolean contains(Character c) {
        return contains0(root, c);
    }

    // O(n)
    private boolean contains0(Node node, Character c) {
        if (node == null) {
            return false;
        }
        if (node.data < c) {
            return contains0(node.right, c);
        } else if (node.data > c) {
            return contains0(node.left, c);
        } else {
            return true;
        }
    }

    // O(n)
    public String toString() {
        if (isEmpty())
            return "#";
        return root.toString();
    }

    public static void example3() {
        BinarySearchTree t = new BinarySearchTree();

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

        System.out.println(t.contains('B'));
        System.out.println(t.contains('X'));


    }

    public static void main(String[] args) {
        example3();
    }
}