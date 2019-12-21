package com.zhangllhome.io.netty.googleproto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyHttpHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writ
        channelHandlerContext.channel().writeAndFlush()
    }
}
