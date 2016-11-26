package com.audi.redis.controller.user;

import java.util.Date;
import java.util.concurrent.Callable;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import com.audi.redis.model.User;
import com.audi.redis.service.RedisCacheUtil;
import com.audi.redis.util.Executor;
import com.audi.redis.util.LongTermTaskCallback;
import com.audi.redis.util.LongTimeAsyncCallService;

//@WebServlet(urlPatterns = "/user/async", asyncSupported = true)
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
    private RedisCacheUtil<User> redisCache;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) {
		System.out.println("admin/index");
		User u=redisCache.getCacheObject("user");
		System.out.println(redisCache);
    	System.out.println(u.getName());
		return "admin/index"; 
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		System.out.println("403");
		
		return "index"; 
	}
	
	@RequestMapping(value = "/async",method = RequestMethod.GET)
	public String async(HttpServletRequest request,HttpServletResponse response, Model model) {
		System.out.println("async");
		//在子线程中执行业务调用，并由其负责输出响应，主线程退出
         AsyncContext ctx = request.startAsync();
        //ctx.setTimeout(30*1000);
         new Thread(new Executor(ctx,response)).start();
        //ctx.start(new Executor(ctx));  
        System.out.println("结束Servlet的时间：" + new Date() + ".");
		return "index"; 
	}
	
	@RequestMapping("/view")
    public Callable<String> callableWithView(final Model model,HttpServletRequest request) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                model.addAttribute("foo", "bar");
                model.addAttribute("fruit", "apple");
                System.out.println("anync...............");
                Thread.sleep(10000);
                return "index";
            }
        };
    }
	
	@RequestMapping("/mvc29")
	@ResponseBody
	public DeferredResult<String> mvc29() {

	    DeferredResult<String> deferredResult = new DeferredResult<String>();
	    dealInOtherThread(deferredResult);
	    return deferredResult;

	}

	private void dealInOtherThread(DeferredResult<String> deferredResult) {
	    try {
	    	System.out.println("----------异步请求处理开始---");
	        Thread.sleep(10000);
	    } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    deferredResult.setResult("index");
	}
	
	/*@RequestMapping(value="/asynctask", method = RequestMethod.GET)
	public DeferredResult<ModelAndView> asyncTask(){
	    DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>();
	    LongTimeAsyncCallService longTimeAsyncCallService=new LongTimeAsyncCallService();
	    System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
	    longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
	        @Override
	        public void callback(Object result) {
	            System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
	            ModelAndView mav = new ModelAndView("remotecalltask");
	            mav.addObject("result", result);
	            deferredResult.setResult(mav);
	        }
	    });
	}*/
	
	
}
