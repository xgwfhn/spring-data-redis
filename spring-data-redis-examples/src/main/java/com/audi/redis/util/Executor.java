package com.audi.redis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Executor 	implements Runnable {
	 private AsyncContext ctx = null;
	 ServletRequest request=null;
	 HttpServletResponse response=null;
	    public Executor(AsyncContext ctx,HttpServletResponse response){
	        this.ctx = ctx;
	        request = ctx.getRequest(); 
	        this.response=response;
	        System.out.println(request.getParameter("name"));
	        ctx.complete();
	    }

	    public void run(){
	        try {
	            //等待十秒钟，以模拟业务方法的执行
	            Thread.sleep(10000);
	            
	            List<String> books = new ArrayList<String>();   
	            books.add("java");  
	            books.add("oracle");  
	            books.add("j2ee");  
	            request.setAttribute("books", "5555555555555"); 
	           // request.setAttribute("test", "ddddddddd");
	            request.getRequestDispatcher("/index.jsp").forward(request, response);
	            System.out.println("业务处理完毕的时间：" + new Date() + ".");
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
