package com.phei.netty.frame.fixedLen;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class EchoServerHandler extends ByteToMessageDecoder{
    
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx , Object msg) throws UnsupportedEncodingException{
//	ByteBuf buf = (ByteBuf)msg;
//	byte[] req = new byte[buf.readableBytes()];
//	buf.readBytes(req);
//	String body = new String(req,"UTF-8").substring(0,req.length
//		-System.getProperty("line.separator").length());
//	System.out.println("The time server receive order : " + body + "; the counter is : " + ++counter);
//	String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(
//		System.currentTimeMillis()).toString() : "BAD ORDER";
//	currentTime = currentTime + System.getProperty("line.separator");
//	ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//	ctx.write(resp);
	
	
	
//	String body = (String) msg;
//	System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
//	String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
//		System.currentTimeMillis()).toString() : "BAD ORDER";
//	currentTime = currentTime + System.getProperty("line.separator");
//	ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//	ctx.writeAndFlush(resp);
	
//	String body = (String) msg;
//	System.out.println("This is " + ++counter + " times receive client : [" + body +"]");
//	body += "$_";
//	ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
//	ctx.writeAndFlush(echo);
	
	System.out.println("Receive client : [" + msg + "]");
	
    }
    public void channelReadComplete(ChannelHandlerContext ctx){
	ctx.flush();
    }
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
	ctx.close();
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
	    List<Object> out) throws Exception {
	// TODO Auto-generated method stub
	
    }
    
    
    
}
