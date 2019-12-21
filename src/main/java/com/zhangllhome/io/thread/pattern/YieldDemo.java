package com.zhangllhome.io.thread.pattern;

public class YieldDemo extends  Thread{
    public YieldDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldDemo ta=new YieldDemo("hello1");
        YieldDemo tb=new YieldDemo("hello2");
        ta.start();
        tb.start();
    }
}
