package com.zhangllhome.io.nio;

import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class FileChannelDemo {
    public static void main(String[] args) {
//        try {
        FileChannel fileChannel = null;
        try {
//            fileChannel = FileChannelImpl.open(FileDescriptor.in,
//                    System.getProperty("user.dir")+"/data/file01.txt",true,false,false,
//                    new FileInputStream(new File(System.getProperty("user.dir")+"/data/file01.txt")));
            fileChannel = new FileInputStream(new File(System.getProperty("user.dir")+"/data/file01.txt")).getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int read = fileChannel.read(byteBuffer);
            byteBuffer.flip();
            // array方法很重要
            System.out.println(new String(byteBuffer.array(),0,read));
//            byteBuffer.flip();
//            while (byteBuffer.hasRemaining()){
//                System.out.println(111);
//                byte b = byteBuffer.get();
//                StringBuilder stringBuilder = new StringBuilder(b);
//                System.out.println(stringBuilder);
//            }

            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
