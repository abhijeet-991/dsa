package beginner_problems;

import java.util.Stack;

public class StackQueue {
    Stack<Integer> stack;
    public StackQueue() {
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        if (!stack.isEmpty()) {
            return removeBottomElement(stack);
        }
        throw new IllegalArgumentException("Stack is empty");
    }

    private int removeBottomElement(Stack<Integer> stack) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        }
        int bottom = removeBottomElement(stack);
        stack.push(top);
        return bottom;
    }

    public int peek() {
        return returnBottomOfStack(stack);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    private int returnBottomOfStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Stack is empty, peek operation not allowed");
        }
        int top = stack.pop();
        if (stack.isEmpty()) {
            stack.push(top);
            return top;
        }
        int bottom = returnBottomOfStack(stack);
        stack.push(top);
        return bottom;
    }
}