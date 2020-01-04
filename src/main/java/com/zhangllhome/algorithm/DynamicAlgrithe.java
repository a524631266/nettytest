package com.zhangllhome.algorithm;

public class DynamicAlgrithe {
    public static int Max= 9;
    public static  int core = Integer.MIN_VALUE;
//    public static int = 9;
    public static void main(String[] args) {
        int[] things  = {2,2,4,6,3};
        findMax(things,0, 0, things.length);
        System.out.println(core);
    }
    public static void findMax(int[] things,int state,int weigth, int n){

        if (weigth == state || state == n) {

            int score = weigth;
            setCore(score);
            return;
        }

        findMax(things, state + 1, weigth + things[state], n);
        if(weigth + things[state+1] <= Max){
            findMax(things, state + 1, weigth + things[state+1],n);
        }
    }
    public static void setCore(int weigth){
        if(weigth <= Max && weigth > core) core = weigth;
    }
}
