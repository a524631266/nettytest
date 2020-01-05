package com.zhangllhome.multithread.pattern.observer;

public class BinaryObserver extends Observer {
    @Override
    public void update() {
        System.out.println("binary strin " + this.subject.getState());
    }
}
