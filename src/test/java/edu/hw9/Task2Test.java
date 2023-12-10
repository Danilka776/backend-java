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
    @DisplayName("Checking the efficiency of finding directories with more than 5 files")
    void getDirectories() {
        // given
        String path = System.getProperty("user.dir");
        String pathName = path+"/src/test/java/edu/hw9/";
        // when
        List<File> getDirectory = Task21.FileSearchTask.getBigDirectory(pathName);
        String[] stringArrayOfDirectory = new String[getDirectory.size()];
        for (int i = 0; i < getDirectory.size(); i++) {
            stringArrayOfDirectory[i] = getDirectory.get(i).toString();
        }

        // then
        assertArrayEquals(stringArrayOfDirectory, new String[]{
            path+"/src/test/java/edu/hw9/TestFolder",
        });
    }

    @Test
    @DisplayName("Checking the efficiency of finding files by predicate: size > 10 and ends on '.txt'")
    void getFiles() {
        // given
        String pathName = "src/test/java/edu/hw9/TestFolder";
        int size = 10;
        String end = ".txt";

        // when
        List<File> getFiles = Task22.FileSearchTask.getFileByPredicate(pathName, size, end);
        String[] stringArrayOfFiles = new String[getFiles.size()];
        for (int i = 0; i < getFiles.size(); i++) {
            stringArrayOfFiles[i] = getFiles.get(i).getAbsolutePath();
        }
        String path = System.getProperty("user.dir");
        // then
        assertArrayEquals(stringArrayOfFiles, new String[]{
            path+"/src/test/java/edu/hw9/TestFolder/test6.txt",
        });
    }

}
