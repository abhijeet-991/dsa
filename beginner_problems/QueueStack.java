package beginner_problems;

import java.util.LinkedList;
import java.util.Queue;
public class QueueStack {
    Queue<Integer> queue;

    public QueueStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.remove());
            size--;
        }
    }

    public int pop() {
        return queue.remove();
    }

    public Integer top() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}