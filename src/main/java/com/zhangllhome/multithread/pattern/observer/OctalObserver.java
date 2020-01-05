package com.zhangllhome.multithread.pattern.observer;

public class OctalObserver extends Observer {
    @Override
    public void update() {
        System.out.println("Octal strin " + this.subject.getState());
    }
}
