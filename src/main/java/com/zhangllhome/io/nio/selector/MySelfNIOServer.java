package com.zhangllhome.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class MySelfNIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//
        while (true){
//
            System.out.println("selectkeys size: " + selector.selectedKeys().size());
            System.out.println("keys size ：" + selector.keys().size());
            if(selector.select(1000) == 0){
                System.out.println("继续等待");
                continue;
            }
//            if(selector.selectNow() == 0){
//                System.out.println("继续等待");
//                continue;
//            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
//                    accept.configureBlocking(false);
                    System.out.println("223232");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }else if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(byteBuffer);
                    System.out.println("从客户端中读取"+ new String(byteBuffer.array()));
                }
                iterator.remove();
            }
//
//            for (SelectionKey selectionKey : selectionKeys) {
//
//                if(selectionKey.isAcceptable()){
//                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
//                    SocketChannel socketChannel = channel.accept();
////                    accept.configureBlocking(false);
//                    System.out.println("channel hashcode" + socketChannel.hashCode());
//                    socketChannel.configureBlocking(false);
//                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
//                }else if(selectionKey.isReadable()){
//                    SocketChannel channel = (SocketChannel) selectionKey.channel();
//                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
//                    channel.read(byteBuffer);
//                    System.out.println("从客户端中读取"+ new String(byteBuffer.array()));
//                }
//            }
//
//            // 这一步很重要，是理解NIO的关键，因为，这里是可以做清理工作的，只有删除掉，下次select才不会读取数据，因为这个事件已经结束了
//            selectionKeys.clear();
            System.out.println("2222");
        }
    }
}
