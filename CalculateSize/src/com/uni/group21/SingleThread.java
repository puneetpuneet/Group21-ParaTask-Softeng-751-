package com.uni.group21;

import java.io.File;

public class SingleThread {	
	private long size;
	private String path;
	
	 public void init() {
		path = new Constant().path;
		File file=new File(path);
		
		long start = System.nanoTime();
	    System.out.println("Start time is :  " + start);
	    
		long result = calculateSize(file);
		System.out.println("Size of the file is :   "+ result+  "  bytes");
		
		long end = System.nanoTime();
		System.out.println("End time is :  " + end);
		
		long seconds = (end - start)/1000000000;
		System.out.println("Total time taken   : " + seconds +"  seconds");
	}

	public long calculateSize(File selectedFile) {
		if (selectedFile.isFile())
		{
			size += selectedFile.length();
		}
		else if(selectedFile.isDirectory()) {
			
			File[] files = selectedFile.listFiles();
			
			if(files!=null)
			{
				for(File childFile: files)
				{
					calculateSize(childFile);
				}
			}
		}
		return size;
	}
}
