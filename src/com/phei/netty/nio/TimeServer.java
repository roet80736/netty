package com.phei.netty.nio;

public class TimeServer {
    
    
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
	    }
	    
	}
	MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
	new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
	
	
	
	
	
    }
}
