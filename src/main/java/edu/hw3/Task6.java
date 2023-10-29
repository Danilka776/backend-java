package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Task6 {

    private Task6() {
    }

    public static class Stock {
        String name;
        Integer value;

        Stock (String name, Integer val) {
            this.name = name;
            this.value = val;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    static class StockMarket {
        Queue<Stock> stocks = new PriorityQueue<>(Comparator.comparingInt(Stock::getValue));
        StockMarket() {
        };
        /** Добавить акцию */
        void add(Stock stock) {
            stocks.add(stock);
        }
        /** Удалить акцию */
        void remove(Stock stock) {
            stocks.remove(stock);
        }
        /** Самая дорогая акция */
        Stock mostValuableStock() {
            return getLast(stocks);
        }

        private Stock getLast(Queue<Stock> pq) {
            Queue<Stock> pqnew = new PriorityQueue<>(pq);
            while(pqnew.size() > 1) {
                pqnew.poll();
            }
            return pqnew.poll();

        }
    }

}
