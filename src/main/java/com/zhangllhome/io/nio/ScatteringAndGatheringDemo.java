package com.zhangllhome.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * scattering 将数据写入buffer的时候 可以采用buffer数组
 * gathering 从buffer数组依次读取数据
 *
 * 这是nio支持多个buffer实现读写的功能
 */
public class ScatteringAndGatheringDemo {
    public static void main(String[] args) throws IOException {

        // 换个新通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress(7000);
        serverSocketChannel.bind(address);

        SocketChannel accept = serverSocketChannel.accept();
        ByteBuffer[] byteBuffers = {
                ByteBuffer.allocate(5),
                ByteBuffer.allocate(3)
        };
        int messagelength = 8;
        while (true){

            int byteRead = 0;
            while(byteRead < messagelength) {
                long l = accept.read(byteBuffers);
                byteRead += l;
            }

            Arrays.asList(byteBuffers).stream().map(
                    byteBuffer -> "postion = " + byteBuffer.position() +
                    ";limit =" + byteBuffer.limit()
                    ).forEach(System.out::println);
//            break;

            // 读出数据并显示咋客户端
            System.out.println("over");
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip()); // 重置偏移量，保证下次能读取

            int byteWrite = 0;
            while (byteWrite < messagelength){

                long write = accept.write(byteBuffers);
                byteWrite+=write;
            }

            System.out.println("out over");
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
        }

    }
}
