package edu.vt.ece.hw4.locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SpinSleepLock implements Lock {
    private final AtomicBoolean state = new AtomicBoolean(false); // Lock state: 0 means unlocked, 1 means locked
    private final int maxSpin; // Maximum number of threads allowed to spin concurrently
    private final AtomicInteger spinningThreads = new AtomicInteger(0); // Total count of currently spinning threads

    public SpinSleepLock() {
        this.maxSpin = 5;
    }

    public void lock() {
        if (spinningThreads.get() < maxSpin) {
            // Enter the spinning phase since we are within the maxSpin limit
            spinningThreads.incrementAndGet(); // Increment global spinning count

            // Spin until we acquire the lock
            while (true) {
                // Try to acquire the lock
                if (!state.getAndSet(true)) {
                    spinningThreads.decrementAndGet(); // Successfully acquired, decrement spinning count
                    return;
                }
            }
        } else {
            // If maxSpin limit is reached, enter wait state
            synchronized (this) {
                try {
                    // Double-check state before waiting
                    if (state.get()) { // Only wait if lock is still held
                        this.wait(); // Go to sleep and wait to be notified
                    }
                } catch (InterruptedException e) {
                    System.out.println("Wait exception");
                }
            }
        }
    }

    public void unlock() {
        state.set(false); // Release the lock

        // Notify a waiting thread, if any
        synchronized (this) {
            this.notify(); // Wake up one thread waiting on this lock
        }
    }
}
