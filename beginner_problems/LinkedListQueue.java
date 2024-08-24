package beginner_problems;

public class LinkedListQueue {
    Node head;
    private static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
    public LinkedListQueue() {

    }

    public void push(int x) {
        addNodeInTheEnd(x);
    }

    private void addNodeInTheEnd(int x) {
        Node node = new Node(x);
        if (head == null) {
            head = node;
            return;
        }
        Node itr = head;
        while (itr.next != null) {
            itr = itr.next;
        }
        itr.next = node;
        node.next = null;
    }

    public int pop() {
        if (head.next == null) {
            int ans = head.val;
            head = null;
            return ans;
        }
        return deleteNodeInTheStart();
    }

    private int deleteNodeInTheStart() {
        if (head == null) {
            throw new IllegalArgumentException("queue is empty, can perform pop operation");
        }
        int ans = head.val;
        head = head.next;
        return ans;
    }

    public int peek() {
        if (head == null) {
            throw new IllegalArgumentException("queue is empty, can't perform peek operation");
        }
        return head.val;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
