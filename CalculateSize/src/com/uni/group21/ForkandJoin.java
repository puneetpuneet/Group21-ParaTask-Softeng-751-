package com.uni.group21;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkandJoin extends RecursiveTask<Long>{
	
	private static final long serialVersionUID = 1L;
	private String path;
	private File file;
	private long size;
	
	public ForkandJoin() {
		path = new Constant().path;
		file = new File(path);
	}
	
	public ForkandJoin(File childFile) {
		this.file = Objects.requireNonNull(childFile);
	}

	public void init() {
		
		long start = System.nanoTime();
	    System.out.println("Start time is :  " + start);
	    
		long result = calculateSize(file);
		System.out.println("Size of the file is :   "+ result+  "  bytes");
		
		long end = System.nanoTime();
		System.out.println("End time is :  " + end);
		

		long seconds = (end - start)/1000000000;
		System.out.println("Total time taken   : " + seconds +" seconds");
	
	}
	
	public long calculateSize(File file) {
		ForkJoinPool pool = new ForkJoinPool();
		try {
			return pool.invoke(new ForkandJoin(file));
		}finally {
			pool.shutdown();
		}
	}

	@Override
	protected Long compute() {
		if (file.isFile())
		{
			size += file.length();
		}
		else if(file.isDirectory()) {
			
			File[] files = file.listFiles();
			final List<ForkandJoin> tasks = new ArrayList<>();
			
			if(files!=null)
			{
				for(File childFile: files)
				{
					final ForkandJoin task = new ForkandJoin(childFile);
					task.fork();
					tasks.add(task);
				}
			}
			
			for(final ForkandJoin task: tasks) {
				size+= task.join();
			}
		}
		return size;
	}
}
