package com.zhangllhome.multithread.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int state ;

    public void attach(Observer observer){
        observer.setSubject(this);
        observers.add(observer);
    }


    public void setState(int state) {
        if(state == this.state){
            return;
        }
        this.state = state;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        observers.stream().forEach(
                observer -> observer.update()
        );
    }

    public int getState() {
        return this.state;
    }
}
