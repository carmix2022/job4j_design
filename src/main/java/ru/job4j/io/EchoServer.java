package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    boolean exit = false;
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("/?msg=Bye")) {
                            exit = true;
                        }
                        System.out.println(str);
                    }
                    if (exit) {
                        out.write("HTTP/1.1 499 Client Closed Request\r\n\r\n".getBytes());
                        server.close();
                        System.out.println("\nServer stopped");
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    }
                }
            }
        }
    }
}
