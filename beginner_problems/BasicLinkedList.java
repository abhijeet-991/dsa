package beginner_problems;

public class BasicLinkedList {

    private Node head;
    private Node tail;

    private int size;

    public BasicLinkedList() {
        this.size = 0;
        head = null;
        tail = null;
    }

    public void deleteHeadOfLinkedList() {
        head = head.next;
    }

    public void deleteTailOfLinkedList() {
        Node itr = head;
        while(itr.next.next != null) {
            itr = itr.next;
        }
        tail = itr;
        tail.next = null;
    }

    public void deleteValueXinLinkedList(int x) {
        if (head == null) {
            throw new IllegalArgumentException("The list is empty.");
        }

        // If the node to be deleted is the head node
        if (head.val == x) {
            head = head.next;
            if (head == null) {
                // If the list becomes empty, update the tail as well
                tail = null;
            }
            return;
        }

        Node prev = null;
        Node curr = head;

        // Traverse the list to find the node to be deleted
        while (curr != null && curr.val != x) {
            prev = curr;
            curr = curr.next;
        }

        // If the node was not found
        if (curr == null) {
            throw new IllegalArgumentException("Value " + x + " not found in the list.");
        }

        // If the node to be deleted is found
        prev.next = curr.next;

        // If the deleted node was the tail node, update the tail
        if (curr == tail) {
            tail = prev;
        }
    }


    public void addNodeInTheEnd(int num) {
        Node node = new Node(num);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public void addNodeInTheStart(int num) {
        Node node = new Node(num);
        if (head == null) {
            tail = node;
        } else {
            Node backup = head;
            head = node;
            head.next = backup;
        }
    }

    public void addNodeAtPosK(int num, int k) {
        if (head == null) {
            addNodeInTheStart(num);
        } else {
            Node node = new Node(num);
            size++;
            int idx = 1;
            Node itr = head;
            while (idx < k-1) {
                itr = itr.next;
                idx++;
            }
            node.next = itr.next;
            itr.next = node;
        }
    }

    public void addNodeBeforeX(int num, int x) {
        if (head == null) {
            addNodeInTheStart(num);
        } else {
            Node node = new Node(num);
            Node itr = head;
            while(itr.next != null && (x != itr.next.val)) {
                itr = itr.next;
            }
            node.next = itr.next;
            itr.next = node;
        }
    }

    public Node getHeadNode() {
        return head;
    }

    public int getTailNode(){
        return tail.val;
    }

    public int getSize() {
        return size;
    }

    public void printList(Node temp) {
        while(temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    public DllNode arrayToLinkedList(int [] nums) {
        DllNode head = new DllNode(nums[0]);
        DllNode itr = head;
        for (int i = 1; i < nums.length; i++) {
            DllNode node = new DllNode(nums[i]);
            itr.next = node;
            node.prev = itr;
            itr = itr.next;
        }
        return head;
    }

    public void printDLL() {
        DllNode dllHead = arrayToLinkedList(new int[] {1,2,3,4,5,6});
        while(dllHead != null) {
            System.out.print(dllHead.val + " ");
            dllHead = dllHead.next;
        }
    }

    private static class Node {
        private int val;
        private Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static class DllNode {
        private int val;
        private DllNode prev;
        private DllNode next;

        DllNode(int val) {
            this.val = val;
        }
    }

    @Override
    public String toString() {
        return "BasicLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
