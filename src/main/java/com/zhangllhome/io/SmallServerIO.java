package com.zhangllhome.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SmallServerIO {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8089);
        System.out.println("server start accept");
        while (true){
            Socket accept = server.accept();

            InputStream inputStream = accept.getInputStream();

            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();
            System.out.println(line);

        }
    }
}
