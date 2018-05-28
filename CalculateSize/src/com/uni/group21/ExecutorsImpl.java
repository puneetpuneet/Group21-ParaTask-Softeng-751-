package com.uni.group21;



import java.io.File;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsImpl implements Callable<Long> {
	
	private static long size;
	private static File file;
	private static ExecutorService executor;
	
	public ExecutorsImpl(File file) 
	{
		this.file = Objects.requireNonNull(file);
	}
	
	public ExecutorsImpl() {
		
	}
	
	public void init()
	{ 
		file= new File(new Constant().path);
		long start = System.nanoTime();
	    System.out.println("Start time is :  " + start);
	    
		long result = calculateSize(file);
		 
		System.out.println("Size of the file is :   "+ result+  "  bytes");
		
		executor.shutdown();
		
		long end = System.nanoTime();
		System.out.println("End time is :  " + end);
		
		long seconds = (end - start)/1000000000;
		System.out.println("Total time taken   : " + seconds +" seconds");
	}


	public static long calculateSize(File dSize) {	
		
		File[] files = dSize.listFiles();
		
		executor = Executors.newFixedThreadPool(25);
		
		for(File file: files) {
			try {
				executor.submit(new ExecutorsImpl(file)).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return size;
	}


	@Override
	public Long call() throws Exception {
		if (file.isFile())
		{
			size += file.length();
		}
		else if(file.isDirectory()) {
			calculateDirSize(file);
		}
		
		return size;
	}
	
	public void calculateDirSize(File files) {
		File[] listFile = files.listFiles();
		if(listFile != null) {
			for(File file: listFile) {
				if(file.isFile()) {
					size+=file.length();
				}else if(file.isDirectory()) {
					calculateDirSize(file);
				}
			}
		}
	}
}


