package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {

    private static AtomicInteger value = new AtomicInteger(0);

    Task1() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public int increment(int numTreads) {
        Thread[] threads = new Thread[numTreads];

        for (int i = 0; i < numTreads; i++) {
            threads[i] = new IncrementThread();
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return value.get();
    }

    static class IncrementThread extends Thread {
        @Override
        public void run() {
            value.incrementAndGet();
        }
    }

}
