package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task2Test {
    @Test
    @DisplayName("Checking the efficiency of finding directories with more than 10 files")
    void getDirectories() {
        // given
        String pathName = "/Users/daniltarasov/backend-java/backend-java/.git";
        // when
        List<File> getDirectory = Task21.FileSearchTask.getBigDirectory(pathName);
        String[] stringArrayOfDirectory = new String[getDirectory.size()];
        for (int i = 0; i < getDirectory.size(); i++) {
            stringArrayOfDirectory[i] = getDirectory.get(i).getAbsolutePath();
        }
        // then
        assertArrayEquals(stringArrayOfDirectory, new String[]{
            "/Users/daniltarasov/backend-java/backend-java/.git/objects",
            "/Users/daniltarasov/backend-java/backend-java/.git/hooks",
            "/Users/daniltarasov/backend-java/backend-java/.git/logs/refs/heads",
            "/Users/daniltarasov/backend-java/backend-java/.git/refs/heads"
        });
    }

    @Test
    @DisplayName("Checking the efficiency of finding files by predicate: size > 3300 and ends on '.java'")
    void getFiles() {
        // given
        String pathName = "/Users/daniltarasov/backend-java/backend-java/";
        int size = 3300;
        String end = ".java";

        // when
        List<File> getFiles = Task22.FileSearchTask.getFileByPredicate(pathName, size, end);
        String[] stringArrayOfFiles = new String[getFiles.size()];
        for (int i = 0; i < getFiles.size(); i++) {
            stringArrayOfFiles[i] = getFiles.get(i).getAbsolutePath();
        }
        // then
        assertArrayEquals(stringArrayOfFiles, new String[]{
            "/Users/daniltarasov/backend-java/backend-java/src/test/java/edu/project1/Project1Test.java",
            "/Users/daniltarasov/backend-java/backend-java/src/test/java/edu/hw1/Task8Test.java",
            "/Users/daniltarasov/backend-java/backend-java/src/main/java/edu/hw9/Task1.java",
        });
    }

}
