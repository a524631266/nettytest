package com.zhangllhome.io.nio;

import java.nio.ByteBuffer;

public class ReadOnlyDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < buffer.capacity() ; i++) {
            buffer.put((byte) (i + 2));
        }

        buffer.flip();

        ByteBuffer byteBuffer = buffer.asReadOnlyBuffer();

        for (int i = 0; i < buffer.capacity(); i++) {
//            byteBuffer.put((byte) i); //抛错
            byte b = byteBuffer.get();
            System.out.println((int) b);
        }
    }
}
