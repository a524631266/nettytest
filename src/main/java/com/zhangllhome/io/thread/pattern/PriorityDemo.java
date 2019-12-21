package com.zhangllhome.io.thread.pattern;

public class PriorityDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        System.out.println("default priority" + t1.getPriority());
        Thread thread = new Thread(() -> {
            Thread inner = new Thread();
            System.out.println("innter priority" + inner.getPriority());
        });
        thread.setPriority(7);
        thread.start(); // 只有在start的时候才会默认创建用户组
        System.out.println("outher priority" + thread.getPriority());
    }
}
