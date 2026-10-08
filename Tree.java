
class Node {
    Character data;
    Node left;
    Node right;
    Node parent;

    @Override
    public String toString() {
        return String.format("(%s %c %s)", left == null ? "#":left.toString(), data, right == null ? "#":right.toString());
    }
}

public class Tree {

    public static String toString(Node n) {
        
        return n.toString();
    }

    /**
     *     A
     *    / \
     *   B   #
     *  /  \
     * #    C
     *    /   \
     *   E     D
     *  / \   / \
     * #   F G   #
     * 
     * A
     * +#
     * +B
     *  +C
     *   +E
     *    +F
     *    +#
     *   +D
     *    +#
     *    +G
     *  +#
     * 
     * ((# B ((# E (# F #)) C ((# G #) D #))) A #)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Tree!");

        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        Node d = new Node();
        Node e = new Node();
        Node f = new Node();
        Node g = new Node();

        a.data = 'A';
        b.data = 'B';
        c.data = 'C';
        d.data = 'D';
        e.data = 'E';
        f.data = 'F';
        g.data = 'G';

        b.parent = a;
        c.parent = b;
        e.parent = c;
        d.parent = c;
        f.parent = e;
        g.parent = d;

        a.left = b;
        b.right = c;
        c.left = e;
        c.right = d;
        e.right = f;
        d.left = g;

        System.out.println(toString(a));
    }
}