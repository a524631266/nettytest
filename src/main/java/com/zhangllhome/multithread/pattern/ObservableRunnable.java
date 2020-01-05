package com.zhangllhome.multithread.pattern;

import sun.plugin.viewer.LifeCycleManager;

public abstract class ObservableRunnable implements Runnable {
    // listener 就是 observer
    protected LifeCycleListener listener;

    public ObservableRunnable(final LifeCycleListener listener){
        this.listener = listener;
    }

    protected void notifyChange(final RunableEvent event){
        listener.onEvent(event);

    }

    public enum RunableState{
        RUNNABLE,RUNNING,DONE, ERROR;
    }
    public static class  RunableEvent{
        private final RunableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunableEvent(RunableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }

}
