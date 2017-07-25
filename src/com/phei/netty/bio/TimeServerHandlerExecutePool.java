package com.phei.netty.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;

public class TimeServerHandlerExecutePool {

    
    private ExecutorService executor;
    
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
	executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L,
		TimeUnit.SECONDS,new ArrayBlockingQueue<java.lang.Runnable>(queueSize));
    }
    public void execute(java.lang.Runnable task){
	executor.execute(task);
    }
}
