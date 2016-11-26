package com.audi.redis.util;

import java.io.File;

public class FindJavaThreadInTaskManager {
	
	 public static void main(String[] args) {        
	        Thread thread = new Thread(new Worker());        
	        thread.start();    
	 }    
	 
	 static class Worker implements Runnable {        
	        @Override        
	        public void run()  {            
	            while (true) {                
	                System.out.println("Thread Name:" + Thread.currentThread().getName());
	                	  
						try {
							Thread.sleep(3000);
							File f=new File("D:\\33\\aa.txt");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
	                }       
	             }    
	 }
}
