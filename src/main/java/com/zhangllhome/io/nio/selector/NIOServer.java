package com.zhangllhome.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建serversocketchannal
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //
        Selector selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(6666));
        // 非堵塞
        serverSocketChannel.configureBlocking(false);
        // serverSocketChannal 注册大selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            if(selector.select(1000) == 0 ){ //没有事件发生
                System.out.println("服务器等待了1s,wu fuwu");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端俩接成功" + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }else if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("客户端" + new String(buffer.array()));
                }
            }
            // 手动从集合中移动
        }
    }
}
