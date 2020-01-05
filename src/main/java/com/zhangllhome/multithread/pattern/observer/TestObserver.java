package com.zhangllhome.multithread.pattern.observer;

public class TestObserver {
    public static void main(String[] args) {
        Observer observer1 = new HexObserver();
        Observer observer2 = new OctalObserver();
        Observer observer3 = new BinaryObserver();

        Subject subject = new Subject();
        subject.attach(observer1);
        subject.attach(observer2);
        subject.attach(observer3);

        subject.setState(2);
    }
}
