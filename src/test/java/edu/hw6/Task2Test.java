package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static edu.hw6.Task2.cloneFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Checking for correct coping for 3 times")
    void isCorrect() throws IOException {
        // given
        Path path = Paths.get("src/test/java/edu/hw6/Tinkoff Bank Biggest Secret.txt");

        // when
        cloneFile(path);
        cloneFile(path);
        cloneFile(path);

        // then
        File file1 = new File(path.toString().substring(0, path.toString().length() - 4) + " — копия"+".txt");
        assertThat(file1.exists()).isTrue();
        File file2 = new File(path.toString().substring(0, path.toString().length() - 4) + " — копия (2)"+".txt");
        assertThat(file1.exists()).isTrue();
        File file3 = new File(path.toString().substring(0, path.toString().length() - 4) + " — копия (3)"+".txt");
        assertThat(file1.exists()).isTrue();

        file1.delete();
        file2.delete();
        file3.delete();


    }


}
