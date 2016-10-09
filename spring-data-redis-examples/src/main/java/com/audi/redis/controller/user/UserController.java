package com.audi.redis.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
