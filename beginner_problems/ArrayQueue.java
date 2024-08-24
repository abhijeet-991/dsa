package beginner_problems;

public class ArrayQueue {
    int[] arr;
    int idx;
    int frontElement;
    public ArrayQueue() {
        arr = new int[101];
        idx = -1;
        frontElement = -1;
    }

    public void push(int x) {
        idx++;
        arr[idx] = x;
        if(idx == 0) {
            frontElement++;
        }
    }

    public int pop() {
        if (frontElement != -1 && frontElement <= idx) {
            return arr[frontElement++];
        }
        throw new IllegalArgumentException("Can't pop Queue is empty!");
    }

    public int peek() {
        if (frontElement != -1 && frontElement <= idx) {
            return arr[frontElement];
        }
        throw new IllegalArgumentException("Queue is empty!");
    }

    public boolean isEmpty() {
        if (idx == -1) {
            return true;
        }
        return frontElement > idx;
    }

    public void print() {
        System.out.print("Queue -> ");
        for (int i = frontElement; i <= idx; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}