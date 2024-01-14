package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static edu.project1.Main.LOGGER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Checking the correctness of database synchronization")
    void correctDatabase() throws InterruptedException {
        // given
        Task3.Person person1 = new Task3.Person(1, "Jon", "jon@mail", "123456");
        Task3.Person person2 = new Task3.Person(2, "Bob", "bob@mail", "7892342");
        Task3.Person person3 = new Task3.Person(3, "Karen", "karen@mail", "12234345");
        Task3.Person person4 = new Task3.Person(4, "Sam", "sam@mail", "12345");
        Task3.PersonDatabase database = new Task3.MyPersonDatabase();

        // when
        Thread thread1 = new Thread(() -> {
            database.add(person2);
            database.delete(person1.id());
        });
        Thread thread2 = new Thread(() -> {
            database.add(person1);
        });
        Thread thread3 = new Thread(() -> {
            database.add(person3);
            database.add(person4);
            database.delete(person4.id());
            synchronized (database) {
                assertThat(database.findByAddress("jon@mail")).isEqualTo(database.findByPhone("123456"));
            }
        });

        // then
        thread2.start();
        thread1.start();
        thread3.start();

        thread2.join();
        thread1.join();
        thread3.join();
    }
}
