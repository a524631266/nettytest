package com.zhangllhome.multithread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 利用copletableFuture实现泡茶行为
 * 使得业务关注分离,体现高维护的作用
 * 该方法是聚合关系的体现
 *
 *
 * Completable 的意思是,其内部以及banging完成执行的操作,即
 * 帮你完成创建线程的意思,没有线程,future是不能执行的
 */
public class CompletableFutureAggreationRelationDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> task01 = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T1: 烧开水");
            sleep(15, TimeUnit.SECONDS);
        });
        CompletableFuture<String> task02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2: 洗茶杯");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2: 洗茶叶");
            sleep(1, TimeUnit.SECONDS);

            return "龙井";
        });
//        // 合并方式1
//        String s = task01.thenCombine(task02, (__, tf) -> {
//            System.out.println("T1拿到茶叶:" + tf);
//            System.out.println("T1 : 泡茶");
//            return "上茶:" + tf;
//        }).get();
//        System.out.println(s);

        // 合并方式2
        System.out.println("@###################");
        CompletableFuture<String> future = task01.thenCombine(task02, (__, tf) -> {
            System.out.println("T1拿到茶叶:" + tf);
            System.out.println("T1 : 泡茶");
            return "上茶:" + tf;
        });
        String join = future.join();
        System.out.println(join);



    }

    static void sleep(long time,TimeUnit unit){
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
