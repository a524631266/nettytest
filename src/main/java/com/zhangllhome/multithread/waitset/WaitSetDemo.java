package com.zhangllhome.multithread.waitset;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 1. 所有对象都有一个WatiSet 用来存放嗲用该对象的wait方法的线程,并进入等待block 此时会切换上下文吗?
 * 2. 线程被notify之后不一定立即执行,而是要抢锁
 * 3. 被唤醒的机率是随机的
 */
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
