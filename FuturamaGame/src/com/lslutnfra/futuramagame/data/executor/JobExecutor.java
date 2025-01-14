package com.lslutnfra.futuramagame.data.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.lslutnfra.futuramagame.domain.executor.ThreadExecutor;


public class JobExecutor implements ThreadExecutor{

	private static final int INITIAL_POOL_SIZE = 3;
	private static final int MAX_POOL_SIZE = 5;
	private static final int KEEP_ALIVE_TIME = 10;
	private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
	
	private final ThreadPoolExecutor threadPoolExecutor;
	private final BlockingQueue<Runnable> workQueue;
	
	public JobExecutor(){
		this.workQueue = new LinkedBlockingQueue<Runnable>();
		this.threadPoolExecutor = new ThreadPoolExecutor(
				INITIAL_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				KEEP_ALIVE_TIME_UNIT,
				this.workQueue);
	}
	
	@Override
	public void execute(final Runnable runnable) {
		this.threadPoolExecutor.execute(runnable);
	}
}
