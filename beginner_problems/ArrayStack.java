package beginner_problems;

public class ArrayStack {
    int topElementIndex;
    int[] arr;
    public ArrayStack() {
        topElementIndex = -1;
        arr = new int[101];
    }

    public void push(int x) {
        topElementIndex++;
        arr[topElementIndex] = x;
    }

    public int pop() {
        if (arr.length != 0) {
            return arr[topElementIndex--];
        }
        throw new IllegalArgumentException("Can't pop since already empty");
    }

    public int top() {
        if (topElementIndex > -1) {
            return arr[topElementIndex];
        }
        throw new IllegalArgumentException("Stack is empty");
    }

    public boolean isEmpty() {
        return topElementIndex != 0;
    }
}
