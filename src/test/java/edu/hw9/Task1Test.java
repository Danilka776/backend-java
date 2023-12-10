package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Сhecking the efficiency of calculating statistics")
    void getStatistic() throws InterruptedException {
        // given
        BlockingQueue<HashMap<String, double[]>> queue = new ArrayBlockingQueue<>(10);
        edu.hw9.Task1.StatsCollector collector = new edu.hw9.Task1.StatsCollector(queue);
        Thread producer1 = new Thread(() -> {
            try {
                collector.push("Average", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread producer2 = new Thread(() -> {
            try {
                collector.push("Max", new double[]{2.0, 1.5, 0.8, 4.3, 2.7});
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer2.start();
        producer1.start();


        // when
        try {
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var res = collector.stats();

        // then
        for (var metric : res) {
            if (metric.statistic.containsKey("Max")) {
                assertThat(metric.statistic.get("Max")).isEqualTo(4.3);
            } else if (metric.statistic.containsKey("Average")) {
                assertThat(metric.statistic.get("Average")).isEqualTo(1.39);
            }
        }
    }

}
