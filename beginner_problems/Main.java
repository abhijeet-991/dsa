package beginner_problems;

import java.util.Arrays;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        patters();
        basicConcepts();
        basicArray();
        basicString();
        basicLinkedList();
        basicDoublyLinkedList();
        basicStack();
        basicQueue();
        stackUsingQueue();
        queueUsingStack();
        stackUsingLinkedList();
        queueUsingLinkedList();
    }

    private static void queueUsingLinkedList() {
        LinkedListQueue queue = new LinkedListQueue();
        queue.push(3);
        queue.push(7);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.isEmpty());
    }

    private static void stackUsingLinkedList() {
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.isEmpty());
    }

    private static void queueUsingStack() {
        StackQueue queue = new StackQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("Front Element popped " + queue.pop());
        System.out.println(queue.peek());
    }

    private static void stackUsingQueue() {
        QueueStack queueStack = new QueueStack();
        queueStack.push(1);
        queueStack.push(2);
        queueStack.push(3);
        queueStack.push(4);
        queueStack.push(5);
    }

    private static void basicQueue() {
        ArrayQueue queue = new ArrayQueue();
        queue.push(5);
        queue.push(10);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.isEmpty());
        queue.push(2);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push(3);
        queue.push(4);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.isEmpty());
        queue.print();
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.isEmpty());
        queue.push(10);
        System.out.println(queue.isEmpty());
    }

    private static void basicStack() {
        ArrayStack stack = new ArrayStack();
        System.out.println(stack.isEmpty());
        stack.push(12);
        stack.push(15);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
    }

    private static void basicDoublyLinkedList() {
        BasicLinkedList basicLinkedList = new BasicLinkedList();
        basicLinkedList.printDLL();
    }

    private static void basicLinkedList() {
        BasicLinkedList basicLinkedList = new BasicLinkedList();
        basicLinkedList.addNodeInTheEnd(1);
        basicLinkedList.addNodeInTheEnd(2);
        basicLinkedList.addNodeInTheEnd(3);
        basicLinkedList.addNodeInTheEnd(4);
        basicLinkedList.addNodeInTheEnd(5);
        basicLinkedList.addNodeInTheStart(20);
        basicLinkedList.addNodeAtPosK(30, 4);
        basicLinkedList.addNodeBeforeX(100, 5);
        basicLinkedList.deleteHeadOfLinkedList();
        basicLinkedList.deleteTailOfLinkedList();
        basicLinkedList.deleteValueXinLinkedList(100);
        System.out.println(basicLinkedList.getHeadNode());
        System.out.println(basicLinkedList.getTailNode());
        System.out.println(basicLinkedList.getSize());
        System.out.print("LinkedList ------> ");
        basicLinkedList.printList(basicLinkedList.getHeadNode());
    }

    private static void basicString() {
        BasicString basicString = new BasicString();
        System.out.println(basicString.reverseString(new Vector<>(Arrays.asList('a', 'b', 'c', 'd', 'e'))));
        System.out.println(basicString.largeOddNum("1924819271294914"));
        System.out.println(basicString.longestCommonPrefix(new String[] {"flowers", "fly", "flow", "flight"}));
        System.out.println(basicString.isomorphicString("egg", "add"));
        System.out.println(basicString.rotateString("abcde", "cdeab"));
        System.out.println(basicString.anagramStrings("anagram", "nagaram"));
        System.out.println(basicString.frequencySort("eat"));
    }

    private static void basicArray() {
        BasicArrays basicArrays = new BasicArrays();
        System.out.println(basicArrays.sum(new int[]{1, 2, 3, 4}, 4));
        System.out.println(basicArrays.countOdd(new int[]{1,2,3,4,5,6}, 6));
        int[] arr = new int[]{1,2,3,4,5};
        basicArrays.reverse(arr, 5);
        Arrays.stream(arr).forEach(System.out::print);
        System.out.println(basicArrays.arraySortedOrNot(new int[]{1,2,3,4}, 4));
    }

    private static void basicConcepts() {
        BasicConcepts basicConcepts = new BasicConcepts();
        System.out.println(basicConcepts.GCD(12, 6));
        System.out.println(basicConcepts.countDigit(123));
        System.out.println(basicConcepts.countOddDigit(12125297));
        System.out.println(basicConcepts.isPalindrome(121));
        System.out.println(basicConcepts.largestDigit(15215219));
        System.out.println(basicConcepts.factorial(5));
        System.out.println(basicConcepts.isArmstrong(123));
        System.out.println(basicConcepts.isPerfect(52));
        System.out.println(basicConcepts.isPrime(5));
        System.out.println(basicConcepts.LCM(12,6));
        System.out.println(basicConcepts.primeUptoN(1000));
        Arrays.stream(basicConcepts.divisors(12)).forEach(System.out::println);
    }

    private static void patters() {
        Patterns patterns = new Patterns();
        patterns.pattern1(5);
        patterns.pattern2(5);
        patterns.pattern3(5);
        patterns.pattern4(5);
        patterns.pattern5(5);
        patterns.pattern6(5);
        patterns.pattern7(5);
        patterns.pattern8(5);
        patterns.pattern9(5);
        patterns.pattern10(5);
        patterns.pattern11(5);
        patterns.pattern12(5);
        patterns.pattern13(5);
        patterns.pattern14(5);
        patterns.pattern15(5);
        patterns.pattern16(5);
        patterns.pattern17(5);
        patterns.pattern18(5);
        patterns.pattern19(5);
        patterns.pattern20(5);
        patterns.pattern21(5);
        patterns.pattern22(5);
    }
}
