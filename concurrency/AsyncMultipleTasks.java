package concurrency;

import java.util.concurrent.CompletableFuture;

public class AsyncMultipleTasks {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> printNumber(1));

        CompletableFuture<Void> chain = task1
                .thenRun(() -> printNumber(2))
                .thenRun(() -> printNumber(3));

        chain.join();
        System.out.println("All tasks done!");
    }

    private static void printNumber(int number) {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " prints: " + number);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
