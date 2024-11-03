package edu.vt.ece.hw4.backoff;

public class ExponentialBackoff implements Backoff {
    private int attempt = 0;

    @Override
    public void backoff() throws InterruptedException {
        attempt++;
        long delay = (long) Math.pow(2, attempt);
        // System.out.printf("EXP delay: %d, attempt: %d%n", delay, attempt);
        Thread.sleep(delay); // Delay based on exponential backoff
    }
}
