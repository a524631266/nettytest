package com.zhangllhome.multithread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTask02 {
    static class Task01 implements Callable<String> {
        private final FutureTask<String> f2;

        Task01(FutureTask<String> f2) {
            this.f2 = f2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T1: 烧开水");
            TimeUnit.SECONDS.sleep(15);

            String tf  = f2.get();

            System.out.println("T1拿到茶叶:" + tf);
            System.out.println("T1 : 泡茶");
            return "上茶:" + tf;
        }
    }
    static class Task02 implements Callable<String> {


        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T2: 洗茶杯");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("T2: 洗茶叶");

            TimeUnit.SECONDS.sleep(1);

            return "龙井";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task02 task02 = new Task02();
        FutureTask<String> f2 = new FutureTask<>(task02);
        FutureTask<String> f1 = new FutureTask<>(new Task01(f2));

        new Thread(f1).start();
        new Thread(f2).start();
        System.out.println(f1.get());
    }
}
