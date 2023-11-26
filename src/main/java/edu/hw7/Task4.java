package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import static edu.project1.Main.LOGGER;

public class Task4 {

    private Task4() {
    }

    private static final double BOUNDS = 0.5;
    private static final List<Integer> SIMULATIONS = List.of(10_000_000, 100_000_000, 1_000_000_000);

    @SuppressWarnings("RegexpSinglelineJava")
    public static List<Long> getTimeOneThreadMonteCarlo() {
        List<Long> timeResult = new ArrayList<>();
        System.out.println("Single thread result: ");
        for (Integer simulation : SIMULATIONS) {
            long start = System.currentTimeMillis();
            double calculatedPi = calculatePiOneThread(simulation);
            long finish = System.currentTimeMillis();
            long time = finish - start;
            timeResult.add(time);
            System.out.println("On %d simulation pi = %f time = %d мс".formatted(simulation, calculatedPi, time));
        }
        return timeResult;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static List<Long> getTimeMultiThreadMonteCarlo() {
        List<Long> timeResult = new ArrayList<>();
        int numProcessors = Runtime.getRuntime().availableProcessors();
        List<Long> singleThreadTime = getTimeOneThreadMonteCarlo();
        System.out.println("Multi thread result: ");

        for (int numThreads = 2; numThreads <= numProcessors; numThreads++) {
            for (int i = 0; i < SIMULATIONS.size(); i++) {
                long start = System.currentTimeMillis();
                double calculatedPi = calculatePiMultiThread(SIMULATIONS.get(i), numThreads);
                long finish = System.currentTimeMillis();
                long time = finish - start;
                timeResult.add(time);
                System.out.println("Number of threads is " + numThreads);
                System.out.println("On %d simulations pi = %f time = %d мс"
                    .formatted(SIMULATIONS.get(i), calculatedPi, time));


                double speedup = (double) singleThreadTime.get(i) / time;
                System.out.println("Ускорение: " + speedup);

                double error = Math.abs(Math.PI - calculatedPi);
                System.out.println("Погрешность: " + error);
                System.out.println();
                timeResult.add(time);
            }

        }
        return timeResult;
    }

    @SuppressWarnings("MagicNumber")
    public static double calculatePiOneThread(int n) {
        int totalCount = 0;
        int circleCount = 0;
        Random rn = new Random();
        for (int i = 0; i < n; i++) {
            double x = rn.nextDouble();
            double y = rn.nextDouble();
            if (Math.pow(x - BOUNDS, 2) + Math.pow(y - BOUNDS, 2) < Math.pow(BOUNDS, 2)) {
                circleCount++;
            }
            totalCount++;
        }
        return 4 * ((double) circleCount / totalCount);
    }

    @SuppressWarnings("MagicNumber")
    public static double calculatePiMultiThread(int numSimulations, int numThreads) {
        AtomicInteger[] circleCountArray = new AtomicInteger[numThreads];
        AtomicInteger[] totalCountArray = new AtomicInteger[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            circleCountArray[i] = new AtomicInteger(0);
            totalCountArray[i] = new AtomicInteger(0);

            final int threadIdx = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numSimulations / numThreads; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();

                    if (Math.pow(x - BOUNDS, 2) + Math.pow(y - BOUNDS, 2) < Math.pow(BOUNDS, 2)) {
                        circleCountArray[threadIdx].incrementAndGet();
                    }
                    totalCountArray[threadIdx].incrementAndGet();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        }

        int totalCircleCount = 0;
        int totalCount = 0;

        for (int i = 0; i < numThreads; i++) {
            totalCircleCount += circleCountArray[i].get();
            totalCount += totalCountArray[i].get();
        }

        return 4 * ((double) totalCircleCount / totalCount);
    }

}
