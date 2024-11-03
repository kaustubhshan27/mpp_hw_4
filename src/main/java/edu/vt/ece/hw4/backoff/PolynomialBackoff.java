package edu.vt.ece.hw4.backoff;

public class PolynomialBackoff implements Backoff {
    private int attempt = 0;

    @Override
    public void backoff() throws InterruptedException {
        attempt++;
        long delay = (long) Math.pow(attempt, 3);
        // System.out.printf("POLY delay: %d, attempt: %d%n", delay, attempt);
        Thread.sleep(delay); // Delay increases based on polynomial exponent
    }
}
