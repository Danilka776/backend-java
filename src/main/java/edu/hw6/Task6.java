package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static class PortScanner {

        private static final int MIN_PORT = 0;
        private static final int MAX_PORT = 49151;
        private static final int RESPONSE_CODE = 200;

        /*
        public static void main(String[] args) {
            System.out.printf("%-9s%-7s%-30s\n", "Протокол", "Порт", "Сервис");
            for (int port = MIN_PORT; port <= MAX_PORT; port++) {
                scannerPort("TCP", port);
                scannerPort("UDP", port);
            }
        }
         */

        @SuppressWarnings("RegexpSinglelineJava")
        private static void scannerPort(String protocol, int port) {
            try {
                InetAddress localhost = InetAddress.getLocalHost();

                Socket socket = new Socket();
                DatagramSocket datagramSocket = new DatagramSocket();

                socket.connect(new InetSocketAddress(localhost, port), RESPONSE_CODE);
                if (!socket.isConnected()) {

                    System.out.printf("%s%d%s\n", protocol, port, "Свободен");
                    socket.close();
                    datagramSocket.close();
                    return;
                }

                String serviceName = getServiceName(port);
                System.out.printf("%-9s%-7d%-30s\n", protocol, port, serviceName);

                socket.close();
                datagramSocket.close();
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        }

        @SuppressWarnings("MagicNumber")
        private static String getServiceName(int port) {
            return switch (port) {
                case 135 -> "EPMAP";
                case 137 -> "Служба имен NetBIOS";
                case 138 -> "Служба датаграмм NetBIOS";
                case 139 -> "Служба сеансов NetBIOS";
                case 143 -> "IMAP";
                case 445 -> "Microsoft-DS Active Directory";
                case 843 -> "Adobe Flash";
                case 1900 -> "Simple Service Discovery Protocol (SSDP)";
                case 3128 -> "HTTPS Proxy";
                case 3702 -> "Динамическое обнаружение веб-служб";
                case 5353 -> "Многоадресный DNS";
                case 5355 -> "Link-Local Multicast Name Resolution (LLMNR)";
                case 17500 -> "Dropbox";
                case 27017 -> "MongoDB";
                default -> "";
            };
        }
    }
}
