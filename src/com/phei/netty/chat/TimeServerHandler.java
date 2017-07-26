package com.phei.netty.chat;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TimeServerHandler extends ByteToMessageDecoder{
    
    @Override
    public void channelRead(ChannelHandlerContext ctx , Object msg) throws UnsupportedEncodingException{
	ByteBuf buf = (ByteBuf)msg;
	byte[] req = new byte[buf.readableBytes()];
	buf.readBytes(req);
	String body = new String(req,"UTF-8");
	System.out.println("The time server receive order : " + body);
	String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(
		System.currentTimeMillis()).toString() : "BAD ORDER";
	ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
	ctx.write(resp);
	
	
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
