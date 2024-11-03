package edu.vt.ece.hw4.bench;

import edu.vt.ece.hw4.barriers.Barrier;

public class BarrierTestThread extends Thread {
    private final Barrier barrier;
    private final int iterations;
    private long elapsedTime;

    public BarrierTestThread(Barrier barrier, int iterations) {
        this.barrier = barrier;
        this.iterations = iterations;
    }

    // Represents work done before reaching the barrier
    private void foo() {
        // Simulate work with a loop or computational task
        for (int i = 0; i < 1000; i++) {
            // Some computation or delay to represent work
        }
    }

    // Represents work done after passing the barrier
    private void bar() {
        // Simulate post-barrier work with a loop or task
        for (int i = 0; i < 1000; i++) {
            // Additional computation or delay
        }
    }

    @Override
    public void run() { // The main execution method of the thread
        foo();            // Perform the pre-barrier work
        long startTime = System.currentTimeMillis();
        barrier.enter();  // Wait at the barrier for other threads
        elapsedTime += System.currentTimeMillis() - startTime;
        bar();            // Perform the post-barrier work
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
