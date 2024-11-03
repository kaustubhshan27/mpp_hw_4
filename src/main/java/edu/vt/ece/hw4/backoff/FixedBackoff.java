package edu.vt.ece.hw4.backoff;

public class FixedBackoff implements Backoff {
    private int attempt = 0;

    @Override
    public void backoff() throws InterruptedException {
        attempt++;
        long delay = attempt * 2;
        Thread.sleep(delay); // Constant delay
    }
}
