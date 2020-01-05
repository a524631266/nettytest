package com.zhangllhome.multithread.pattern;

import org.apache.camel.util.TimeUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadLifeCycleObserver implements LifeCycleListener {
    private final Object LOCK = new Object();


    public void concurrentQuery(List<String> ids){
        if(ids == null || ids.isEmpty()){
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try{
                    notifyChange(new RunableEvent(RunableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id" + id);
                    TimeUnit.SECONDS.sleep(1);

                    int a = 1/0;
                    notifyChange(new RunableEvent(RunableState.DONE, Thread.currentThread(), null));

                }catch (Exception e){
                    notifyChange(new RunableEvent(RunableState.ERROR, Thread.currentThread(), e));

                }
            }
        }, id).start());
    }


    @Override
    public void onEvent(ObservableRunnable.RunableEvent event) {
        synchronized (LOCK){
            System.out.println("THE RUNNABLE [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");

            if(event.getCause()!=null) {
                System.out.println("THE RUNNABLE is failure [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");
                event.getCause().printStackTrace();;
            }
        }
    }
}
