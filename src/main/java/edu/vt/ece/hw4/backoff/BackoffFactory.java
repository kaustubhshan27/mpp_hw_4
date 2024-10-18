package edu.vt.ece.hw4.backoff;

public class BackoffFactory {
    public static Backoff getBackoff(String name) {
        switch(name) {
            case "Exponential":
                return new ExponentialBackoff();
            case "Fibonacci":
                return new FibonacciBackoff();
        }
        return null;
    }
}
