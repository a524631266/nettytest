package com.zhangllhome.multithread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTask01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            return "134";
        });
        // Task没有start方法,只有在给定线程(工作单位)工作状态下,才能返回一个结果
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        System.out.println(stringFutureTask.get());
    }
}
