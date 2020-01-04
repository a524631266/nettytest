package com.zhangllhome.multithread.waitset;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSetDemo {
    public final static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(0,10)
                .forEach(i ->
                        new Thread(String.valueOf(i)){
                            @Override
                            public void run() {
                                synchronized (object){
                                    Optional.of(currentThread().getName() + " into wait set").ifPresent(System.out::println);
                                    try {
                                        object.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Optional.of(currentThread().getName() + " leave wait set").ifPresent(System.out::println);
                                }
                            }
                        }.start());
        Thread.sleep(1000);
        IntStream.rangeClosed(0,10)
                .forEach(i ->
                        new Thread(String.valueOf(i)){
                            @Override
                            public void run() {
                                synchronized (object){
                                    object.notify();
                                }
                            }
                        }.start());
    }
}
