package com.zhangllhome.io.netty;


class InnerClass{
    public InnerClass() {
        System.out.println("haha");
    }
}

public class StaticDoOrNot {
    private static InnerClass innerClass = new InnerClass();
    public  static String name = "name";
}
