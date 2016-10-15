package com.audi.redis.util;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncServlet
 */
//@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("async");
		//在子线程中执行业务调用，并由其负责输出响应，主线程退出
         AsyncContext ctx = request.startAsync();
        //ctx.setTimeout(30*1000);
        // new Thread(new Executor(ctx)).start();
         executor.execute(new Executor(ctx));
        //ctx.start(new Executor(ctx));  
        System.out.println("结束Servlet的时间：" + new Date() + ".");
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
