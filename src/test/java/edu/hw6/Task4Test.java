package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Checking for right output to file")
    void rightFileOutput() throws IOException {
        // given
        String text = "Programming is learned by writing programs. ― Brian Kernighan.";

        // when
        Task4.outputStreamComposition(text);
        // then
        BufferedReader reader = new BufferedReader(new FileReader("src/test/java/edu/hw6/outputTask6.txt"));
        String line = reader.readLine();
        assertThat(line).isEqualTo(text);
        reader.close();
    }


}
