package edu.hw8.Task1;

import java.util.stream.Stream;

public class ClientMain {
    private static final int NUM_OF_THREAD = 3;

    private ClientMain() {
    }

    public static void main(String[] args) {
        Stream.generate(() -> new Thread(() -> new Client().sendMessage("личности")))
            .limit(NUM_OF_THREAD).forEach(Thread::start);
        Stream.generate(() -> new Thread(() -> new Client().sendMessage("оскорбления")))
            .limit(NUM_OF_THREAD).forEach(Thread::start);
        Stream.generate(() -> new Thread(() -> new Client().sendMessage("интеллект")))
            .limit(NUM_OF_THREAD).forEach(Thread::start);
    }
}
