package com.zhangllhome.multithread.pattern.observer;

public class HexObserver extends Observer {
    @Override
    public void update() {
        System.out.println("Hex strin " + this.subject.getState());
    }
}
