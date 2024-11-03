package edu.vt.ece.hw4.backoff;

public class LinearBackoff implements Backoff {
    private int attempt = 0;

    public void backoff() throws InterruptedException {
        attempt++;
        long delay = attempt;
        // System.out.printf("LIN delay: %d, attempt: %d%n", delay, attempt);
        Thread.sleep(delay); // Delay increases linearly with each attempt
    }
}
