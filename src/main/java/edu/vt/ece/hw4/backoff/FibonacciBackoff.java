package edu.vt.ece.hw4.backoff;

public class FibonacciBackoff implements Backoff {
    private int attempt = 0;

    @Override
    public void backoff() throws InterruptedException {
        attempt++;
        long delay = fibonacci(attempt);
        // System.out.printf("FIB delay: %d, attempt: %d%n", delay, attempt);
        Thread.sleep(delay);
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return b;
    }
}
