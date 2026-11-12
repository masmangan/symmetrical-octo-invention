import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
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
        int f = (right == null ? -1 : right.height) - (left == null ? -1 : left.height);
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

/**
 * 
 * AVLTree
 */
public class AVLTree {

    private Node root;

    public AVLTree() {
        root = null;
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
        if (node.data < c) {
            node.right = add(node.right, c);
            if (node.fb() >= 2) {
                if (node.right.fb() < 0) {
                    node.right = rotateRight(node.right);
                }
                node = rotateLeft(node);
            }
        } else if (node.data > c) {
            node.left = add(node.left, c);
            if (node.fb() <= -2) {
                if (node.left.fb() > 0) {
                    node.left = rotateLeft(node.left);
                }
                node = rotateRight(node);
            }
        }
        node.height = 1 + Math.max(h(node.left), h(node.right));
        return node;
    }

    private Node rotateLeft(Node k1) {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = 1 + Math.max(h(k1.left), h(k1.right));
        k2.height = 1 + Math.max(h(k2.left), h(k2.right));
        return k2;
    }

    private Node rotateRight(Node k2) {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = 1 + Math.max(h(k2.left), h(k2.right));
        k1.height = 1 + Math.max(h(k1.left), h(k1.right));
        return k1;
    }

    private int h(Node node) {
        if (node == null)
            return -1;
        return node.height;
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
    
    public String toString() {
        if (isEmpty())
            return "#";
        return root.toString();
    }

    public static void example3() {
        AVLTree t = new AVLTree();

        System.out.println(t);
        System.out.println(t.isEmpty());

        t.add('C');

        System.out.println(t);
        System.out.println(t.isEmpty());

        t.add('A');

        System.out.println(t);
        System.out.println(t.isEmpty());

        t.add('B');

        System.out.println(t);
        System.out.println(t.isEmpty());
    }

    public static void main(String[] args) {
        example3();
    }
}