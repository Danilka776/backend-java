package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Checking for emptiness during initialization")
    void isEmpty() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();

        // when


        // then
        assertThat(disk.isEmpty()).isTrue();
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();

    }
    @Test
    @DisplayName("Checking for no emptiness after adding")
    void isNoEmpty() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();

        // when
        disk.put("key", "value");

        // then
        assertThat(disk.isEmpty()).isFalse();
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

    @Test
    @DisplayName("Checking for emptiness after adding and popping")
    void isEmptyAfterOperations() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();
        String key = "key";

        // when
        disk.put(key, "value");
        disk.remove(key);

        // then
        assertThat(disk.isEmpty()).isTrue();
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

    @Test
    @DisplayName("Checking the presence of an element by key")
    void isContainKey() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();
        String key = "key";

        // when
        disk.put(key, "value");

        // then
        assertThat(disk.containsKey(key)).isTrue();
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

    @Test
    @DisplayName("Checking the presence of an element by value")
    void isContainValue() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();
        String key = "key";

        // when
        disk.put(key, "value");

        // then
        assertThat(disk.containsKey(key)).isTrue();
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

    @Test
    @DisplayName("Checking that it is impossible to delete a non-existent element")
    void cantDelete() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();
        String key = "key";
        String key1 = "key1";

        // when
        disk.put(key, "value");
        String res = disk.remove(key1);

        // then
        assertThat(res).isNull();
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

    @Test
    @DisplayName("Checking the correctness of the get function")
    void tryGet() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();
        disk.put("key1", "value");

        // when
        String res = disk.get("key1");

        // then
        assertThat(res).isEqualTo("value");
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

    @Test
    @DisplayName("Replace existing value by key")
    void replaceVal() {
        // given
        Task1.DiskMap disk = new Task1.DiskMap();
        disk.put("key1", "value1");
        disk.put("key2", "value2");
        disk.put("key3", "value3");
        assertThat(disk.size()).isEqualTo(3);

        // when
        disk.put("key2", "value22222");

        // then
        assertThat(disk.get("key2")).isEqualTo("value22222");
        File f = new File("src/main/java/edu/hw6/associativeArray.txt");
        f.delete();
    }

}
