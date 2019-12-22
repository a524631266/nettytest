package com.zhangllhome.io.netty.googleproto;

import java.lang.reflect.InvocationTargetException;

public class TestClass {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ABC abc = ABC.class.getConstructor().newInstance();
        System.out.println(abc);
        ABCD abcd = new ABCD();
        System.out.println(abcd.getName());
        ABCD dd = (ABCD) abc;
        dd.getName();
    }
}
