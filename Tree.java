import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

class GenericNode {
    Character data;
    GenericNode left;
    GenericNode right;
    GenericNode parent;

    GenericNode() {
    }

    GenericNode(Character data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("(%s %c %s)", left == null ? "#" : left.toString(), data,
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
        Queue<GenericNode> q = new LinkedList<>();
        List<Character> result = new ArrayList<>();
        q.add(this);
        while (!q.isEmpty()) {
            GenericNode n = q.remove();
            result.add(n.data);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
        return result;
    }
}

public class Tree {

    public static String toString(GenericNode n) {

        return n.toString();
    }

    /**
     * A
     * / \
     * B #
     * / \
     * # C
     * / \
     * E D
     * / \ / \
     * # F G #
     * 
     * A
     * +#
     * +B
     * +C
     * +E
     * +F
     * +#
     * +D
     * +#
     * +G
     * +#
     * 
     * ((# B ((# E (# F #)) C ((# G #) D #))) A #)
     * 
     * @param args
     */
    public static void example1() {
        System.out.println("Tree!");

        GenericNode a = new GenericNode();
        GenericNode b = new GenericNode();
        GenericNode c = new GenericNode();
        GenericNode d = new GenericNode();
        GenericNode e = new GenericNode();
        GenericNode f = new GenericNode();
        GenericNode g = new GenericNode();

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
        System.out.println(a.breadth());
    }

    public static void example2() {
        GenericNode a = new GenericNode('A');
        GenericNode b = new GenericNode('B');
        GenericNode plus1 = new GenericNode('+');

        plus1.left = a;
        plus1.right = b;
        a.parent = b.parent = plus1;

        GenericNode d = new GenericNode('D');
        GenericNode e = new GenericNode('E');
        GenericNode sub1 = new GenericNode('-');

        sub1.left = d;
        sub1.right = e;
        d.parent = e.parent = sub1;

        GenericNode f = new GenericNode('F');
        GenericNode g = new GenericNode('G');
        GenericNode div1 = new GenericNode('/');

        div1.left = f;
        div1.right = g;
        f.parent = div1;
        g.parent = div1;

        GenericNode c = new GenericNode('C');
        GenericNode mult1 = new GenericNode('*');

        mult1.left = c;
        mult1.right = sub1;
        c.parent = sub1.parent = c;

        GenericNode plus2 = new GenericNode('+');

        plus2.left = mult1;
        plus2.right = div1;
        mult1.parent = div1.parent = plus2;

        GenericNode sub2 = new GenericNode('-');

        sub2.left = plus1;
        sub2.right = plus2;
        plus1.parent = plus2.parent = sub2;

        System.out.println(plus1);
        System.out.println(plus1.pre());
        System.out.println(plus1.central());
        System.out.println(plus1.pos());

        System.out.println(sub1);
        System.out.println(sub1.pre());
        System.out.println(sub1.central());
        System.out.println(sub1.pos());

        System.out.println(div1);
        System.out.println(div1.pre());
        System.out.println(div1.central());
        System.out.println(div1.pos());

        System.out.println(mult1);
        System.out.println(mult1.pre());
        System.out.println(mult1.central());
        System.out.println(mult1.pos());

        System.out.println(plus2);
        System.out.println(plus2.pre());
        System.out.println(plus2.central());
        System.out.println(plus2.pos());

        System.out.println(sub2);
        System.out.println(sub2.pre());
        System.out.println(sub2.central());
        System.out.println(sub2.pos());
    }

    public static void main(String[] args) {
        example2();
    }
}