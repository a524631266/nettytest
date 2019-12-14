package com.zhangllhome.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 把abc.png私用transferTo/transferFrome
 * 都可以复制文件按
 */
public class NIOoutinputDemo01 {
    public static void main(String[] args) throws IOException {
        File file = new File("data/abc.png");
        File file1 = new File("data/abcd.png");
//        file1.createNewFile();
        FileInputStream inputStream01 = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);

        FileChannel channel01 = inputStream01.getChannel();
        FileChannel channel02 = fileOutputStream.getChannel();
        // transferTo 也能
//        channel01.transferTo(0,file.length(),channel02);
        // transfer实现From 也能
        channel02.transferFrom(channel01,0, file.length());
        channel01.close();
        channel02.close();
        inputStream01.close();
        fileOutputStream.close();

    }
}

