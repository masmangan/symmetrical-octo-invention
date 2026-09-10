import java.util.Arrays;

class Stack {
    private int[] data;
    private int top;

    public Stack() {
        data = new int[10];
        top = 0;
    }

    public boolean isEmpty() {
        return top <= 0;
    }
    public boolean isFull() {
        return top >= 10;
    }

    public int pop() {
        return data[--top];
    }

    public void push(int e) {
        data[top++] = e;
    }

    public String toString() {
        return String.format("{%d %s}", top, Arrays.toString(data));
    }



public static void main(String[] args) {
    Stack s1;
    s1 = new Stack();

    System.out.println(s1);
    System.out.println(s1.isEmpty());
    System.out.println(s1.isFull());

    s1.push(42);

    System.out.println(s1);
    System.out.println(s1.isEmpty());
    System.out.println(s1.isFull());
}
//@masmangan ➜ /workspaces/symmetrical-octo-invention (main) $ java Stack
//{0 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]}
//true
//false
//{1 [42, 0, 0, 0, 0, 0, 0, 0, 0, 0]}
//false
//false
}