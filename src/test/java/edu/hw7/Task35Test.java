package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task35Test {
    @Test
    @DisplayName("Checking the correctness of database synchronization")
    void correctDatabaseSynchronization() throws InterruptedException {
        // given
        Task3_5.Person person1 = new Task3_5.Person(1, "Jon", "jon@mail", "123456");
        Task3_5.Person person2 = new Task3_5.Person(2, "Bob", "bob@mail", "7892342");
        Task3_5.Person person3 = new Task3_5.Person(3, "Karen", "karen@mail", "12234345");
        Task3_5.Person person4 = new Task3_5.Person(4, "Sam", "sam@mail", "12345");
        Task3_5.PersonDatabase database = new Task3_5.MyPersonDatabase();
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
            assertThat(database.findByAddress("jon@mail")).isEqualTo(database.findByPhone("123456"));
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
