package com.phei.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

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
		    ch.pipeline().addLast(new TimeClientHandler());
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
	new TimeClient().connect(port, "127.0.0.1");
	
	
    }
    
}
