package com.zhangllhome.io.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存copy修改文件
 */
public class MapperByteBufferDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("data/file01.txt");
//        FileOutputStream outputStream = new FileOutputStream(file);
        RandomAccessFile outputStream = new RandomAccessFile(file, "rw");
        FileChannel channel = outputStream.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 1, 4);
        map.put(0, (byte) 'h');

        map.put(5,  (byte)'d');
        channel.close();
        outputStream.close();
    }
}
