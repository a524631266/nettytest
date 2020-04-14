package com.zhangllhome.multithread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 穿行化任务
 */
public class CompletableFutureSeqRelationDemo1 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("洗茶壶");
                    CompletableFutureAggreationRelationDemo1.sleep(1, TimeUnit.SECONDS);
                    return "";
                }
        ).thenApply(s -> {
            System.out.println("洗茶杯");
            CompletableFutureAggreationRelationDemo1.sleep(2, TimeUnit.SECONDS);
            return 0;
        }).thenApply(s -> {
            System.out.println("洗茶叶");
            CompletableFutureAggreationRelationDemo1.sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });

//        future.handle(() -> {
//
//        });

    }
}
