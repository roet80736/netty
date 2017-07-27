package com.phei.netty.frame.fixedLen;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoClient {

    public void connect(int port,String host){
	//配置客户端NIO线程组
	EventLoopGroup group = new NioEventLoopGroup();
	try {
	    Bootstrap b = new Bootstrap();
	    b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
	    .handler(new ChannelInitializer<SocketChannel>(){

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
		    // TODO Auto-generated method stub
//		    ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//		    ch.pipeline().addLast(new StringDecoder());
//		    ch.pipeline().addLast(new EchoClientHandler());
		    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
		    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
		    ch.pipeline().addLast(new StringDecoder());
		    ch.pipeline().addLast(new EchoClientHandler());
		}
		
		
	    });
	    //发起异步连接操作
	    ChannelFuture f = b.connect(host,port).sync();
	    //等待客户端链路关闭
	    f.channel().closeFuture().sync();
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}finally{
	    group.shutdownGracefully();
	}
	
	
    }
    
    public static void main(String[] args) {
	int port = 8080;
	if(args != null && args.length >0){
	    try {
		port = Integer.valueOf(args[0]);
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	}
	new EchoClient().connect(port, "127.0.0.1");
	
	
    }
    
}
