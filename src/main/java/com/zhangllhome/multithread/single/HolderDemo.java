package com.zhangllhome.multithread.single;

public final class HolderDemo {
    private String name = "holder outer";

    public String getName() {
        return name;
    }

    private static class Holder{
        static {
            System.out.println("inner");
        }
        static HolderDemo holderDemo = new HolderDemo();
    }

    public static HolderDemo instance(){
        return Holder.holderDemo;
    }

    public static void main(String[] args) {
//        new HolderDemo();
        HolderDemo.instance();
    }

}
