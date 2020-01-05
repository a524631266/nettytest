package com.zhangllhome.multithread.pattern.observer;

public abstract class Observer {
    public Subject subject ;

    public abstract void update();

    public void setSubject(Subject subject){
        this.subject = subject;
    };
}
