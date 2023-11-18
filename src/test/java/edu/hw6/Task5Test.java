package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Checking for right response on 37570037 id news")
    void correctTitle() {
        // given
        long id = 37570037;
        Task5.HackerNews hackerNews = new Task5.HackerNews();

        // when
        String title = hackerNews.news(37570037);

        // then
        assertThat(title).isEqualTo("JDK 21 Release Notes");
    }


}
