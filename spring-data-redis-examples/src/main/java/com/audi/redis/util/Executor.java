package com.audi.redis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;

public class Executor 	implements Runnable {
	 private AsyncContext ctx = null;
	    public Executor(AsyncContext ctx){
	        this.ctx = ctx;
	    }

	    public void run(){
	        try {
	            //等待十秒钟，以模拟业务方法的执行
	            Thread.sleep(10000);
	            ServletRequest request = ctx.getRequest();  
	            List<String> books = new ArrayList<String>();  
	            books.add("java");  
	            books.add("oracle");  
	            books.add("j2ee");  
	            request.setAttribute("books", books); 
	            request.setAttribute("test", "ddddddddd");
	           // ctx.dispatch("/index.jsp");  
	            System.out.println("业务处理完毕的时间：" + new Date() + ".");
	            ctx.complete();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
