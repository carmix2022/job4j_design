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
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (!str.contains("/?msg=Exit") && !str.contains("/?msg=Hello")) {
                        out.write(str.substring(str.indexOf("/?msg=") + 6, str.lastIndexOf(" ")).getBytes());
                    }
                    if (str.contains("/?msg=Exit")) {
                        out.write("Server is closed".getBytes());
                        server.close();
                    }
                    if (str.contains("/?msg=Hello")) {
                        out.write("Hello".getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}
