package com.zhangllhome.io.netty.googleproto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

public class GoogleServer {
    public static void main(String[] args) throws InterruptedException {

        // eventLoopGroup 主要 由ThreadPerTaskExecutor(就是拥有一个excutor的执行者,真正的执行者)
        // 创建一个拥有一个工厂(DefaultEventExecutorChooserFactory)以及一个
        // SelectorProvider.provider()(Selector 多路复用组建),以及一个默认的SelectStrategyFactory工厂
        // 提供者的线程组的线程池组,元素为 EventExecutor 默认EventLoop拥有executor接口,用来执行任务用的
        // 更进一步就是EventLoop都在一个EventLoopGroup组内,并持有一个执行者的权限,才能工作
        // 同时拥有选择测策略,比如链表依次轮询方式,以及拥有一个拒绝执行的处理器(句柄)的处理方式
//    在NioEventLoopGroup中
//        protected EventLoop newChild(Executor executor, Object... args) throws Exception {
//            return new NioEventLoop(this, executor, (SelectorProvider) args[0],
//                    ((SelectStrategyFactory) args[1]).newSelectStrategy(), (RejectedExecutionHandler) args[2]);
//        }

        // AbstractNioMessageChannel
//        在生成不同运行环境下的selector
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();


//        new NioServerSocketChannel(); 本质上创建一个ServerSocketChannel,稍微做了一些元数据管理,以及其他管理
        try {
            ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler()) // 服务器日志
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            S
//                            pipeline.addLast("decoder", new StringDecoder())
//                                    .addLast("encoder", new StringEncoder())
//                                    .addLast(null);
                            pipeline.addLast("myhttpserver",new HttpServerCodec());
                            pipeline.addLast(new MyHttpHandler());
                        }
                    });
            //NioEventLoop 有一个selector,用来做监听用的
            // NioEventLoop 初始化的是会开启一个openSelector,用来创建selector监听请求
            // 其中openSelector里面做了什么,其实就是返回一个selector,通过一个producer,目前有很多厂家会提供selector的支持,所以就是
            // 还有一个是run方法,既然是Executor那么就一定要按照规范执行run方法,这个是线程执行的必要接口
            // 在run方法内部一旦有任务或者事件产生,就会执行嗯unsafe.read(); 以及 unsafe.write(),
            // 在不停的轮询暂停,这里的轮询是个非常严肃的问题,可大可小

            //bind 方法用的是一种模板模式,
            ChannelFuture channelFuture = bootstrap.bind(9999).sync();

            ChannelFuture sync = channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
