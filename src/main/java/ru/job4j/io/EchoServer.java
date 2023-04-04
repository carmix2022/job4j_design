package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "";
                    boolean firstLine = true;
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (firstLine) {
                            firstLine = false;
                            if (!str.contains("/?msg=Exit") && !str.contains("/?msg=Hello")) {
                                answer = str.substring(str.indexOf("/?msg=") + 6, str.lastIndexOf(" "));
                            }
                            if (str.contains("/?msg=Exit")) {
                                answer = "Server is closed";
                                server.close();
                            }
                            if (str.contains("/?msg=Hello")) {
                                answer = "Hello";
                            }
                        }
                        System.out.println(str);

                    }
                    System.out.println();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }
}
