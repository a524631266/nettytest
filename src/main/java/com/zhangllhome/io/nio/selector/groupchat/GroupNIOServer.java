package com.zhangllhome.io.nio.selector.groupchat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;


public class GroupNIOServer {
    private static Logger LOG = LoggerFactory.getLogger(GroupNIOServer.class);



    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            if(selector.select(10000) == 0) {// 这个10000 ms等待,在这期间一旦发送事件也会触发
//                LOG.info("selector闲置轮询中．．．．");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    InetSocketAddress remoteAddress = (InetSocketAddress)socketChannel.getRemoteAddress();
                    System.out.println(remoteAddress + " has loading");
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    String response = remoteAddress.getPort() + " has online";
                    sendMessageToOthers(response,selectionKey, selector);
                } else if(selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer contents = (ByteBuffer) selectionKey.attachment();
//                    readContent(socketChannel, contents); // 限制长度
                    int read = socketChannel.read(contents);
                    String response = ((InetSocketAddress)socketChannel.getRemoteAddress()).getPort() + " says: ";
                    if(read == -1) { // 关闭链接

                        // 用来转发消息
                        response += "I has shutdown!!!";
                        selectionKey.cancel();
                        socketChannel.close();
                    }else{
                        response += new String(contents.array(), 0 ,read);
                    }
                    sendMessageToOthers(response,selectionKey, selector);
                    //　性能会高吗
                    contents.flip();
                    contents.clear();
                }
            }
            selectionKeys.clear();
            System.out.println("信息交流结束");
//            LOG.info("信息交流结束");
        }

    }
    private static void sendMessageToOthers(String response,SelectionKey selectionKey,Selector selector) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        // 转发信息
        for (SelectionKey others : keys) {
            if((others.channel() instanceof SocketChannel) && !others.equals(selectionKey)){
                System.out.println("给其他用户发送信息:" + ((SocketChannel) others.channel()).getRemoteAddress());
                SocketChannel channel = (SocketChannel) others.channel();

//                            channel.write(contents);
                channel.write(ByteBuffer.wrap(response.getBytes()));

            }
        }
    }


    private static String readContent(SocketChannel socketChannel, ByteBuffer byteBuffer) throws IOException {
        String result = "";
        byteBuffer.clear();
        while (true){
            int read = socketChannel.read(byteBuffer);
            if(read == 0) {
                break;
            }
            int readlen = 0;
            while (readlen < read){

                result += (char) byteBuffer.get(readlen);
                readlen += 1;
            }
            byteBuffer.flip();
        }

        byteBuffer.clear();
        return  result;
    }
}
