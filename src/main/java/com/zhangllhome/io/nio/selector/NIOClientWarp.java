package com.zhangllhome.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClientWarp {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 6666));
        if(!connect){
            while (!socketChannel.finishConnect()){
                System.out.println("链接一些时间，不会堵塞，可以不用堵塞，可以做其他语句");

            }
        }
        String str = "helhlo , zhangl";
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
        // 发送数据
        socketChannel.write(wrap);
        System.in.read();
    }
}
