package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static final int BUFFER_SIZE = 1024;
    public static final int NUM_OF_THREADS = 2;

    static ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

    @SuppressWarnings("RegexpSinglelineJava")
    public void start() {
        try (ServerSocketChannel channel = ServerSocketChannel.open()) {
            channel.socket().bind(new InetSocketAddress(HOST, PORT));
            System.out.println("Server socket created, command console reader for listen to server commands");


            while (channel.isOpen()) {
                SocketChannel client = channel.accept();
                executorService.submit(() -> handleClient(client));
            }

            executorService.shutdown();
            System.out.println("Executor shutdown");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void handleClient(SocketChannel client) {
        try {
            System.out.println("Handling client");
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int bytesRead;
            while ((bytesRead = client.read(buffer)) != -1) {
                buffer.flip();
                byte[] bytes = new byte[bytesRead];
                buffer.get(bytes);

                String request = new String(bytes);
                System.out.println(request);
                String response = getResponse(request);
                System.out.println(response);

                client.write(ByteBuffer.wrap(response.getBytes()));
                System.out.println("Response sent");

                buffer.clear();
            }

            System.out.println("Client disconnected");
            client.close();
            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getResponse(String request) {
        return switch (request) {
            case "личности" -> "Не переходи на личности там, где их нет.";
            case "оскорбления" ->
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.";
            case "глупый" ->
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
            case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления.";
            default -> "Не понимаю вас";
        };
    }
}
