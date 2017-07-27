package io.netty.handler.codec.msgpack;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;

import com.phei.netty.codec.serializable.UserInfo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class EchoClientHandler extends ByteToMessageDecoder{
    
    private static final Logger logger = Logger.getLogger(EchoClientHandler.class.getName());
    
//    private final ByteBuf firstMessage;
    
    private int counter;
//    private byte[] req;
    static final String ECHO_REQ = "Hi,feng,Welcome to Netty.$_";
    
    private final int sendNumber;
    /**
     * 
     * <p>Title:</p>
     * <p>Description:</p>
     */
    public EchoClientHandler(int sendNumber){
//	req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
//	firstMessage = Unpooled.buffer(req.length);
//	firstMessage.writeBytes(req);
	this.sendNumber = sendNumber;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
//	ctx.writeAndFlush(firstMessage);
//	ByteBuf message = null;
//	for(int i = 0; i < 100; i++){
//	    message = Unpooled.buffer(req.length);
//	    message.writeBytes(req);
//	    ctx.writeAndFlush(message);
//	}
//	for(int i = 0; i < 10; i++){
//	    ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
//	}
	UserInfo[] infos = UserInfo();
	for(UserInfo infoE : infos){
	    ctx.write(infoE);
	}
	ctx.flush();
    }
    
    private UserInfo[] UserInfo(){
	UserInfo[] userInfos = new UserInfo[sendNumber];
	UserInfo userInfo = null;
	for(int i = 0; i < sendNumber; i++){
	    userInfo = new UserInfo();
	    userInfo.setUserID(i);
	    userInfo.setUserName("ABCDEFG --->" + i);
	    userInfos[i] = userInfo;
	}
	return userInfos;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException{
//	ByteBuf buf = (ByteBuf)msg;
//	byte[] req = new byte[buf.readableBytes()];
//	buf.readBytes(req);
//	String body = new String(req,"UTF-8");
//	System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
	
//	String body = (String) msg;
//	System.out.println("Now is :" + body + " ; the counter is : " + ++counter);
//	System.out.println("This is " + ++counter + "times receive server : [" + msg + "]");
	
	
	System.out.println("Client receive the msgpack message : " + msg);
	ctx.write(msg);
	
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
	System.out.println(cause.getMessage().toString());
	ctx.close();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
	    List<Object> out) throws Exception {
	// TODO Auto-generated method stub
	
    }
}
