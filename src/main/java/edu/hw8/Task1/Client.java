package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;

    @SuppressWarnings("RegexpSinglelineJava")
    public void sendMessage(String text) {
        InetSocketAddress hostAddress = new InetSocketAddress(HOST, PORT);
        try (SocketChannel client = SocketChannel.open()) {
            client.connect(hostAddress);
            ByteBuffer buf = ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8));
            System.out.println("Подключено к серверу " + HOST + ":" + PORT);
            while (buf.hasRemaining()) {
                client.write(buf);
            }
            System.out.println("Send message");


            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            client.read(buffer);
            System.out.println("Read response");
            buffer.flip();

            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String response = new String(bytes);

            System.out.println("Сервер: " + response);
        } catch (IOException e) {
            throw new RuntimeException("Unable to communicate with server.", e);
        }
    }



}
