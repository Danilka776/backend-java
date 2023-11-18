package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.Task3.AbstractFilter.*;
import static edu.project1.Main.LOGGER;

public class Task3Test {
    @Test
    @DisplayName("Checking code from example")
    void isCorrect() throws IOException {
        // given
        Path dir = Path.of("src/main/java/edu/hw6/tmp.txt");

        // when
        Task3.AbstractFilter filter = REGULAR_FILE
            .and(READABLE)
            .and(sizeGreaterThan(100_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }




        // then



    }


}
