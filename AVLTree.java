
/**
 * 
 */
class AVLNode {
    Character data;
    AVLNode left;
    AVLNode right;
    int height;

    AVLNode(Character data) {
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
}

/**
 * 
 * AVLTree
 */
public class AVLTree {

    private AVLNode root;

    // O(1)
    public AVLTree() {
        root = null;
    }

    // O(1)
    public boolean isEmpty() {
        return root == null;
    }

    // O(log n)
    public void add(Character c) {
        root = add(root, c);
    }

    // O(log n)
    private AVLNode add(AVLNode node, Character c) {
        if (node == null) {
            return new AVLNode(c);
        }

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

    // O(1)
    private AVLNode rotateLeft(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = 1 + Math.max(h(k1.left), h(k1.right));
        k2.height = 1 + Math.max(h(k2.left), h(k2.right));
        return k2;
    }

    // O(1)
    private AVLNode rotateRight(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = 1 + Math.max(h(k2.left), h(k2.right));
        k1.height = 1 + Math.max(h(k1.left), h(k1.right));
        return k1;
    }

    // O(1)
    private int h(AVLNode node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    // O(n)
    public boolean contains(Character c) {
        return contains0(root, c);
    }

    // O(n)
    private boolean contains0(AVLNode node, Character c) {
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
        if (isEmpty()) {
            return "#";
        }
        return root.toString();
    }

    public static void example3() {
        AVLTree t = new AVLTree();

        System.out.println(t);
        System.out.println(t.isEmpty());

        t.add('A');

        System.out.println(t);
        System.out.println(t.isEmpty());

        t.add('C');

        System.out.println(t);
        System.out.println(t.isEmpty());

        t.add('B');
        t.add('E');
        t.add('D');
        t.add('H');

        System.out.println(t);
        System.out.println(t.isEmpty());
    }

    public static void main(String[] args) {
        example3();
    }
}