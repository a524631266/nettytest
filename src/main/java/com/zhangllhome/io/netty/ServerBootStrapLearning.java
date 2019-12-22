package com.zhangllhome.io.netty;

import io.netty.channel.ChannelInboundInvoker;
import io.netty.channel.ChannelOption;

public class ServerBootStrapLearning {
    public static void main(String[] args) {

        System.out.println(StaticDoOrNot.name);
        ChannelOption<Object> zhangll = ChannelOption.newInstance("zhangll");
        boolean zhangll1 = ChannelOption.exists("zhangll");
        System.out.println(zhangll1);
//        ChannelInboundInvoker
    }
}
