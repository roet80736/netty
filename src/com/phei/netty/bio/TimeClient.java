package com.phei.netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    
    /**
     * 
     *TODO
     *@param args
     */
    public static void main(String[] args) {
	int port = 8080;
	if(args != null && args.length > 0){
	    port = Integer.valueOf(args[0]);
	}
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	try {
	    socket = new Socket("127.0.0.1",port);
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(),true);
	    out.println("QUERY TIME ORDER");
	    System.out.println("Send order to server succeed.");
	    String resp = in.readLine();
	    
	} catch (Exception e) {
	    // TODO: handle exception
	}
	
	
	
	
	
    }
}
