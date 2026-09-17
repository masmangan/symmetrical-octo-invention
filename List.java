// Deitel & Deitel
// 7.13, 7.14,
// Seções 7.13 e 7.14
// Capítulo 18
// Capítulo 19
// Seções 20.6 e 20.8
// Capítulo 22

// Cormen et al.
// Seções 10.2 e 10.3

class Node {
    int data;
    Node next;
}

public class List {

    private Node head;
    private int size;

    public List() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node getNodeAt(final int i) {
        int c = 0;
        Node aux = head;
        while (aux != null) {
            if (c == i) {
                return aux;
            }
            aux = aux.next;    
            c = c + 1;     
        }
        throw new IndexOutOfBoundsException();
    }

    public int get(final int i) {
        int c = 0;
        Node aux = head;
        while (aux != null) {
            if (c == i) {
                return aux.data;
            }
            aux = aux.next;    
            c = c + 1;     
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }

    public void add(final int e) {
        Node aux = new Node();
        aux.data = e;

        aux.next = head;
        head = aux;
        size++;
    }

    public void add(final int e, final int i) {
        if (i == 0) {
            add(e);
            return;
        }
        Node aux = new Node();
        aux.data = e;
        Node pre = getNodeAt(i-1);
        Node aft = pre.next;
        aux.next = aft;
        pre.next = aux;

        size++;
    }

    public String toString() {
        String s = "[";
        Node aux = head;
        while (aux != null) {
            s += aux.data;
            aux = aux.next;
            if (aux != null) {
                s+= ", ";
            }
        }
        s += "]";
        return s;
    }


    public static void main(String[] args) {
        List l1;
        l1 = new List();
        System.out.println(l1);

        l1.add(42);
        System.out.println(l1);

        l1.add(13);
        System.out.println(l1);

        for (int i = 0; i < l1.size; i++) {
            System.out.printf("%d %d %n", i, l1.get(i));
            
        }

        l1.add(67, 2);
        System.out.println(l1);        
        //l1.get(4);
    }
}