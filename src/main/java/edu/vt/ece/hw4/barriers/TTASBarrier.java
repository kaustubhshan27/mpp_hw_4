package edu.vt.ece.hw4.barriers;
import edu.vt.ece.hw4.locks.TTASLock;
import edu.vt.ece.hw4.locks.Lock;

public class TTASBarrier implements Barrier {
    private final int n;
    private int counter;
    private Lock lock;

    public TTASBarrier(int n) {
        this.n = n;
        this.counter = 0;
        this.lock = new TTASLock();
    }

    public void enter() {
        // Increment the counter with locking for mutual exclusion
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }

        // Wait until the counter reaches n
        while (counter < n) {
            // Busy-wait until all threads reach the barrier
        }
    }
}
