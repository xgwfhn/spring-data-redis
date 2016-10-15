package com.audi.redis.controller.user;

import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audi.redis.util.Executor;

//@WebServlet(urlPatterns = "/user/async", asyncSupported = true)
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) {
		System.out.println("admin/index");
		return "admin/index"; 
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		System.out.println("403");
		return "admin/index"; 
	}
	
	@RequestMapping(value = "/async",method = RequestMethod.GET)
	public String async(HttpServletRequest request, Model model) {
		System.out.println("async");
		//在子线程中执行业务调用，并由其负责输出响应，主线程退出
         AsyncContext ctx = request.startAsync();
        //ctx.setTimeout(30*1000);
         new Thread(new Executor(ctx)).start();
        //ctx.start(new Executor(ctx));  
        System.out.println("结束Servlet的时间：" + new Date() + ".");
		return "index"; 
	}
}
