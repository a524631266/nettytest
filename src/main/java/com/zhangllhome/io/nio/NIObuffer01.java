package com.zhangllhome.io.nio;

import java.nio.IntBuffer;

public class NIObuffer01 {
    public static void main(String[] args) {
        // buffer的使用
        IntBuffer intBuffer = IntBuffer.allocate(5);
//        向buffle中存数据

        for (int i = 0; i < intBuffer.capacity() -1 ; i++) {
            intBuffer.put(i * 2);
        }
        intBuffer.flip(); // 读写切换,指针重置
//        for (int i = 0; i < intBuffer.capacity(); i++) {
//            System.out.println(intBuffer.get());
//        }
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
