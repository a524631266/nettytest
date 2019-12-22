package com.zhangllhome.io.netty.googleproto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class MyHttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("handler type" + this.hashCode() + " request address" + ctx.channel().remoteAddress());
        System.out.println(msg.getClass());
        if ( msg instanceof HttpRequest) {

            HttpRequest newmsg = (HttpRequest) msg;
            System.out.println("request url : " + newmsg.uri());
            ByteBuf byteBuf = Unpooled.copiedBuffer("{'a': 1}", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);


//            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/json");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
//            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,1);
//            response.headers().set()
            ctx.channel().writeAndFlush(response);
        }

    }
}
