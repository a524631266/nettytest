package com.zhangllhome.io.netty.codec.protobuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("接收客户端的任务进行长时间地处理");
        // 定时处理
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    System.out.println("接收到客户端的任务 : " + msg);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello , 客户端", CharsetUtil.UTF_8));
                    System.out.println("channel hashcode : " + ctx.channel().hashCode());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);

    }
}
