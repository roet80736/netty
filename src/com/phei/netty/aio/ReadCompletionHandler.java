package com.phei.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

public class ReadCompletionHandler implements CompletionHandler<Integer,ByteBuffer>{
    
    private AsynchronousSocketChannel channel;
    
    public ReadCompletionHandler(AsynchronousSocketChannel channel){
	if(this.channel == null){
	    this.channel = channel;
	}
	
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
	// TODO Auto-generated method stub
	attachment.flip();
	byte[] body = new byte[attachment.remaining()];
	attachment.get(body);
	try {
	    String req = new String(body,"UTF-8");
	    System.out.println("The time server receive order : " + req);
	    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new Date(
		    System.currentTimeMillis()).toString() : "BAD ORDER";
	    doWrite(currentTime);
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }
    private void doWrite(String currentTime){
	if(currentTime != null && currentTime.trim().length() >0){
	    byte[] bytes = currentTime.getBytes();
	    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
	    writeBuffer.put(bytes);
	    writeBuffer.flip();
	    channel.write(writeBuffer,writeBuffer,
		    new CompletionHandler<Integer,ByteBuffer>(){
		    @Override
		    public void completed(Integer result,ByteBuffer buffer){
			//如果没有发送完成,继续发送
			if(buffer.hasRemaining()){
			    channel.write(buffer, buffer, this);
			}
		    }

		    @Override
		    public void failed(Throwable exc, ByteBuffer attachment) {
			// TODO Auto-generated method stub
			try {
			    channel.close();
			} catch (Exception e) {
			    // TODO: handle exception
			    e.printStackTrace();
			}
		    }
		
	    });
	}
    }
    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
	// TODO Auto-generated method stub
	try {
	    this.channel.close();
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }
}
