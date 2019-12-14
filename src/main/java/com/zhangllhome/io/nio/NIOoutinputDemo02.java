package com.zhangllhome.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOoutinputDemo02 {
    public static void main(String[] args) throws IOException {
        File file = new File("data/abc.png");
        File file1 = new File("data/abcde.png");
        FileInputStream inputStream01 = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);

        FileChannel channel01 = inputStream01.getChannel();
        FileChannel channel02 = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(true){
            System.out.println("asd");
            byteBuffer.clear();// 重置position
//            public final Buffer clear() {
//                position = 0;
//                limit = capacity;
//                mark = -1;
//                return this;
//            }
            int read = channel01.read(byteBuffer); // read的时候偏移
            if(read != -1){
                byteBuffer.flip(); // write的时候需要重置
//                public final Buffer flip() {
//                    limit = position;
//                    position = 0;
//                    mark = -1;
//                    return this;
//                }
                channel02.write(byteBuffer);
            }else{
                break;
            }


        }

        channel01.close();
        channel02.close();
        inputStream01.close();
        fileOutputStream.close();

    }
}

