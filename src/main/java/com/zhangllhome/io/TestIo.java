package com.zhangllhome.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestIo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            while(true){
                System.out.println("开始建立链接");
                Socket accept = serverSocket.accept();
                executorService.execute(()->{
                    handleCallback(accept);
                });
//                Thread thread = new Thread(() -> {
//                    TestIo.handleCallback(accept);
//                });
//                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static  void handleCallback(Socket socket){
        try {
            System.out.println("开始处理handle");
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            System.out.println(Thread.currentThread().getName());
            while (true){
                int read = inputStream.read(bytes);
                if(read!=-1){
                    System.out.println(new String(bytes,0, read));
                }else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
