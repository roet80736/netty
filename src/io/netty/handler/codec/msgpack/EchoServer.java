package io.netty.handler.codec.msgpack;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {

    public void bind(int port) {

	    // 配置服务端NIO线程组
	    EventLoopGroup bossGroup = new NioEventLoopGroup();
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	    try {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
		    .option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO))
		    .childHandler(new ChildChannelHandler());
		// 绑定端口,同步等待成功
		ChannelFuture f = b.bind(port).sync();

		// 等待服务端监听端口关闭
		f.channel().closeFuture().sync();
	    } catch (Exception e) {
	    // TODO: handle exception
		e.printStackTrace();
	    }finally{
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	    }

    }
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
	    // TODO Auto-generated method stub
//	    arg0.pipeline().addLast(new EchoServerHandler());
//	    arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
//	    arg0.pipeline().addLast(new StringDecoder());
//	    arg0.pipeline().addLast(new EchoServerHandler());
	    
//	    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//	    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
//	    ch.pipeline().addLast(new StringDecoder());
//	    ch.pipeline().addLast(new EchoServerHandler());
	    
//	    ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
//	    ch.pipeline().addLast(new StringDecoder());
//	    ch.pipeline().addLast(new EchoServerHandler());
	    
	    ch.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
	    ch.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
	    ch.pipeline().addLast(new EchoServerHandler());	    
	}
	
    }
    /**
     * 
     *TODO
     *@param args
     */
    public static void main(String[] args) {
	int port = 8080;
	if(args != null && args.length > 0){
	    try {
		port = Integer.valueOf(args[0]);
	    } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	    }
	}
	new EchoServer().bind(port);
    }
}