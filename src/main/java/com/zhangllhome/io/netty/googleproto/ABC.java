package com.zhangllhome.io.netty.googleproto;

public class ABC {

    public final String name;
    public final int age;
    public ABC (){
        this("Dfaul", 10);
    }
    ABC(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        System.out.println(getClass());
        return getClass().getName();
    }
    @Override
    public String toString() {
        return "ABC{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class ABCD extends ABC{

}
