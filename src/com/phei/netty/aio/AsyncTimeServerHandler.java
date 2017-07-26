package com.phei.netty.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeServerHandler implements Runnable{
    
    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;
    
    public AsyncTimeServerHandler(int port){
	this.port = port;
	try {
	    asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
	    asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
	    System.out.println("The time server is start in port : " + port);
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }
    @Override
    public void run() {
	// TODO Auto-generated method stub
	latch = new CountDownLatch(1);
	doAccept();
	try {
	    latch.await();
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }
    public void doAccept(){
	asynchronousServerSocketChannel.accept(this,new AcceptCompletionHandler());
    }
}
