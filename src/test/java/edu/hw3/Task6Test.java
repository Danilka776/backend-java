package edu.hw3;

import edu.hw3.Task6.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Simple input")
    void test1() {
        // given
        Task6.StockMarket market = new Task6.StockMarket();
        Stock stock1 = new Stock("stock1", 100);
        Stock stock2 = new Stock("stock2", 300);
        Stock stock3 = new Stock("stock3", 200);

        // when
        market.add(stock1);
        market.add(stock2);
        market.add(stock3);
        Stock res = market.mostValuableStock();

        // then
        assertThat(res).isEqualTo(stock2);
    }

}
