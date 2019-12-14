package com.zhangllhome.io.nio.zerocopy;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 传统ＩＯ
 * 1. 硬盘copy　　DMA direct memory access 直接内存copy,不使用cpu　，就是直接从硬盘中ｃｏｐｙ到（内核）内存中
 * 2. 系内核内存copy　　（内核）内存通过ＣＰＵ copy到用户态的内存中
 * 3. 用户态内存copy=> sokect　　修改完的内存再回到socket 内核中
 * 4. 网络内存 => 物理网络协议栈中（　DMA direct）　　内核态数据再返回给协议栈（网络程序使用的内存空间）
 *
 * ｍmap 并不是真正的零copy 第二步　是有用户态切换的　４次上下文切换，３次ｃｏｐｙ
 * 1. 硬盘copy　　DMA direct memory access 直接内存copy,不使用cpu　，就是直接从硬盘中ｃｏｐｙ到（内核）内存中
 * 2. 系内核内存＝＞sokect内核 　　（内核）内存通过文件映射到缓存区　　直接到网络内核内存中
 * 3网络内存 => 物理网络协议栈中（　DMA direct）　　内核态数据再返回给协议栈（网络程序使用的内存空间）
 *
 * 适用场景少数据量
 *
 * sendFile　第二步　是没有用户态切换，但是仍然会经过cpu　ｃｏｐｙ　从系统内核到网络内核中
 *  * 1. 硬盘copy　　DMA direct memory access 直接内存copy,不使用cpu　，就是直接从硬盘中ｃｏｐｙ到（内核）内存中
 *  * 2. 系内核内存＝＞sokect内核 　　（内核）内存通过文件映射到缓存区　　直接到网络内核内存中
 *  * 3网络内存 => 物理网络协议栈中（　DMA direct）　　内核态数据再返回给协议栈（网络程序使用的内存空间）
 *
 * 适合场景　大文件传输　　３次上下文切换，最少２次ｃｏｐｙ
 *
 *  零copy
 *  是指　第二步中没有cpu copy的
 *  在ｌｉｎｕｘ　2.4版本中仍然有不过只ｃｏｐｙ相对较少的信息　ｌｅｎｇｔｈ　或　ｏｆｆｓｅｔ
 */

public class TestZero {

    static class NewIOServer{
        void start() throws IOException {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(inetSocketAddress);

            //创建ｂｕｆｆｔｅｒ

            ByteBuffer allocate = ByteBuffer.allocate(4096);
            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                int readcount = 0;
                while (-1 != readcount){

                    try {
                        readcount = socketChannel.read(allocate);



                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //
                    allocate.rewind(); // 倒带　 mark作废
                }
            }

        }
    }

    class NewIOClient{
        void start() throws IOException {
            SocketChannel open = SocketChannel.open();
            open.connect(new InetSocketAddress(7001));
            String filename = "data/file01.txt";

            FileChannel channel = new FileInputStream(filename).getChannel();
            // 准备发送
            long starttime = System.currentTimeMillis();
            // 在window中　一次调用嗯transforeTo，只能发送8M，要分段传送
            long transfercount = channel.transferTo(0, channel.size(), open);
            System.out.println("发送的总的字节数 = " + transfercount + " 耗时"  + (System.currentTimeMillis() - starttime));
        }
    }
    public static void main(String[] args) throws IOException {
        new NewIOServer().start();
    }
}
