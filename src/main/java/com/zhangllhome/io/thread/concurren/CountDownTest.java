package com.zhangllhome.io.thread.concurren;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownTest {
    public static void main(String[] args) throws InterruptedException {
        TimeSocket timeSocket = new TimeSocket();
        new Thread(timeSocket).start();
        Thread.sleep(2000);
        timeSocket.downCount();
    }
}
class TimeSocket implements Runnable {

    private CountDownLatch count;
    TimeSocket(){

    }
    public void downCount(){
        count.countDown();
    }

    @Override
    public void run() {
        System.out.println("count down !!");
        count = new CountDownLatch(1);
        while (true){
            try {
                System.out.println("wait count down");
                count.await();
                System.out.println("next count down");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

