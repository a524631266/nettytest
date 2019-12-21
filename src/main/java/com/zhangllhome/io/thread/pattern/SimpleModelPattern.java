package com.zhangllhome.io.thread.pattern;

import java.util.concurrent.TimeUnit;

public class SimpleModelPattern {
    public SimpleModelPattern(String message){
        System.out.println("start");
        handle(message);
        System.out.println("end");
    }

    protected void handle(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleModelPattern("asd"){
            @Override
            protected void handle(String message) {
                System.out.println("asdfasd");
            }
        };

        Thread thread = new Thread(() -> {
            System.out.println("aasd");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.start(); // 这个种情况是thread生命周期已过,因为2秒过去了
    }


}
