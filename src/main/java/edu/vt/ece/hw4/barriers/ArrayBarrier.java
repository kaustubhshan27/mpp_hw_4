package edu.vt.ece.hw4.barriers;

import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBarrier implements Barrier {
    private final int n;                 // Total number of threads
    private final int[] b;               // Array to coordinate threads
    private final AtomicInteger counter; // To assign a unique index to each thread
    private final ThreadLocal<Integer> threadIndex = ThreadLocal.withInitial(() -> -1); // Thread-local index

    public ArrayBarrier(int n) {
        this.n = n;
        this.b = new int[n];             // Initializes to all 0s by default
        this.counter = new AtomicInteger(0);
    }

    public void enter() {
        // Assign a unique index to each thread on its first call to enter()
        if (threadIndex.get() == -1) {
            threadIndex.set(counter.getAndIncrement());
        }

        int index = threadIndex.get();

        if (index == 0) {
            // Thread 0 starts by setting b[0] to 1
            b[0] = 1;
        } else {
            // Other threads spin until b[i-1] is 1, then set b[i] to 1
            while (b[index - 1] != 1) {
                // Spin-wait until previous element is 1
            }
            b[index] = 1;                 // Signal that this thread has reached the barrier
        }

        // If it's the last thread, set b[n-1] to 2, allowing all threads to proceed
        if (index == n - 1) {
            b[n - 1] = 2;
        } else {
            // Non-final threads wait until b[n-1] is 2 before proceeding
            while (b[n - 1] != 2) {
                // Spin-wait until the last element is 2
            }
        }
    }
}

