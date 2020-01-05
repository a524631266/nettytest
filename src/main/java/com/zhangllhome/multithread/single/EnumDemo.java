package com.zhangllhome.multithread.single;
enum Singleton{
    INSTANCE;
    private byte[] data = new byte[1024];
    Singleton(){
        System.out.println("instance will be initialized imemediately");
    }
    public static void method(){} //触发方法该方法会实例化枚举变量

    public static Singleton getInstance(){
        return INSTANCE;
    }
}
public class EnumDemo  {
    public static void main(String[] args) {
        System.out.println("1111");
        Singleton.method();
    }
}
