package com.zhangllhome.thread;

public class QuestingAboutInterupt {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            Thread th = Thread.currentThread();
            while (true) {
                if(th.isInterrupted()){
                    System.out.println("is itterupt");
                    break;
                }
                System.out.println("next");
                try {
                    synchronized (th) {
//                    Thread.sleep(1000);
                        th.wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(500);
        thread.interrupt();

        Thread.sleep(100000);

    }
}
