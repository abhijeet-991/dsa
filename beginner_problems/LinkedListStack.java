package beginner_problems;

public class LinkedListStack {

    Node head;

    public LinkedListStack() {

    }

    public void push(int x) {
        addToHead(x);
    }

    public int pop() {
        int temp = head.val;
        head = head.next;
        return temp;
    }

    public int top() {
        return getHead();
    }

    public boolean isEmpty() {
        return head == null;
    }


    private void addToHead(int x) {
        Node node = new Node(x);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    private int getHead() {
        if (head != null) {
            return head.val;
        }
        throw new IllegalArgumentException("Stack is empty");
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
