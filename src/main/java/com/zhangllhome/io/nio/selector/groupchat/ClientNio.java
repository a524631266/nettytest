package com.zhangllhome.io.nio.selector.groupchat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientNio {
    private static Logger LOG = LoggerFactory.getLogger(ClientNio.class);
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        if(!connect) {
            while (!socketChannel.finishConnect()){
                LOG.info("do before connection");
            }
        }
        executorService.execute(()->{
            try {
                handledRead(selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            handledWrite(socketChannel);
        });



    }
    public static void  handledRead(Selector selector) throws IOException {

        while (true){
            if(selector.select(1000) == 0){
//                System.out.println("等待链接");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                if(selectionKey.isReadable()){

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

                    int read = socketChannel.read(byteBuffer);
//                    System.out.println("read:" + read);
                    if(read == -1) {
                        System.out.println("服务其已关闭");
                        System.exit(1);
                    }
                    System.out.println(new String(byteBuffer.array(),0,read));
                    byteBuffer.clear();
                }
            }
            selectionKeys.clear();

        }

    }
    public static void handledWrite(SocketChannel socketChannel){
        Scanner scanner = new Scanner(System.in);
        while (true){
//            scanner.hasNext();
            System.out.println("请输入内容:");
            String next = scanner.nextLine();
//            System.out.println(next);
            try {
                socketChannel.write(ByteBuffer.wrap(next.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
