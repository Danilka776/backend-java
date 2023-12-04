package edu.hw8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class Task2 {

    private Task2() {
    }

    public static Integer[] calculateFibonacciSequence(int numOfThreads, int sizeOfSequence) {
        Integer[] sequence = new Integer[sizeOfSequence];
        try (NewThreadPool threadPool = NewThreadPool.create(numOfThreads, sizeOfSequence)) {
            threadPool.start();

            for (int i = 0; i < sizeOfSequence; i++) {
                final int taskNumber = i;
                threadPool.execute(() -> {
                    int result = calculateFibonacci(taskNumber);
                    sequence[taskNumber] = result;
                });
            }

            threadPool.awaitTermination();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return sequence;
    }

    private static int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    public interface ThreadPool extends AutoCloseable {
        void start();

        void execute(Runnable runnable);
    }

    static class NewThreadPool implements ThreadPool {
        private final Thread[] threads;
        private final LinkedBlockingQueue<Runnable> taskQueue;
        private final CountDownLatch latch;

        NewThreadPool(int threadCount, int sequenceSize) {
            this.threads = new Thread[threadCount];
            this.taskQueue = new LinkedBlockingQueue<>();
            this.latch = new CountDownLatch(sequenceSize);
        }

        @Override
        public void start() {
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        while (true) {
                            Runnable task = taskQueue.take();
                            task.run();
                            latch.countDown();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                threads[i].start();
            }
        }

        @Override
        public void execute(Runnable task) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void close() {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }

        public void awaitTermination() throws InterruptedException {
            latch.await();
        }

        public static NewThreadPool create(int numOfThreads, int sequenceSize) {
            return new NewThreadPool(numOfThreads, sequenceSize);
        }
    }

}
