package edu.hw9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public final class Task1 {

    private Task1() {
    }

    public static final int TIME_OUT = 100;

    static class Statistic {
        double[] data;
        Map<String, Double> statistic;

        Statistic(double[] data, Map<String, Double> stat) {
            this.data = data;
            this.statistic = stat;
        }
    }



    static class StatsCollector {
        volatile private BlockingQueue<HashMap<String, double[]>> queue;
        volatile List<Statistic> dataList = new CopyOnWriteArrayList<>();

        public List<Statistic> stats() throws InterruptedException {
            List<Thread> executorThreads = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                Thread executorThread = new Thread(new Executor(queue));
                executorThreads.add(executorThread);
                executorThread.start();
            }

            for (Thread executorThread : executorThreads) {
                executorThread.join();
            }
            return dataList;
        }

        StatsCollector(BlockingQueue<HashMap<String, double[]>> queue) {
            this.queue = queue;
        }

        public void push(String metricName, double[] data) throws InterruptedException {
            HashMap<String, double[]> task = new HashMap<>();
            task.put(metricName, data);
            queue.put(task);
        }

        class Executor implements Runnable {
            private BlockingQueue<HashMap<String, double[]>> queue;

            Executor(BlockingQueue<HashMap<String, double[]>> queue) {
                this.queue = queue;
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        var data = queue.poll(TIME_OUT, TimeUnit.MILLISECONDS);
                        if (data != null) {
                            execute(data);
                        } else {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            @SuppressWarnings("MultipleStringLiterals")
            private void execute(HashMap<String, double[]> task) {
                for (var entry : task.entrySet()) {
                    if (entry.getKey().equals("Sum")) {
                        HashMap<String, Double> res = new HashMap<>();
                        res.put("Sum", Arrays.stream(entry.getValue()).sum());
                        dataList.add(new Statistic(entry.getValue(), res));
                    } else if (entry.getKey().equals("Average")) {
                        HashMap<String, Double> res = new HashMap<>();
                        res.put("Average", Arrays.stream(entry.getValue()).sum() / entry.getValue().length);
                        dataList.add(new Statistic(entry.getValue(), res));
                    } else if (entry.getKey().equals("Max")) {
                        HashMap<String, Double> res = new HashMap<>();
                        res.put("Max", Arrays.stream(entry.getValue()).max().getAsDouble());
                        dataList.add(new Statistic(entry.getValue(), res));
                    } else if (entry.getKey().equals("Min")) {
                        HashMap<String, Double> res = new HashMap<>();
                        res.put("Min", Arrays.stream(entry.getValue()).min().getAsDouble());
                        dataList.add(new Statistic(entry.getValue(), res));
                    }
                }
            }

        }

    }
}
