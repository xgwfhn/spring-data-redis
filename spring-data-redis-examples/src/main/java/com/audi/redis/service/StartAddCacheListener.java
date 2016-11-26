package com.audi.redis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.audi.redis.model.User;



/*
 * 监听器，用于项目启动的时候初始化信息
 */
@Service
public class StartAddCacheListener implements ApplicationListener<ContextRefreshedEvent>
{
    //日志
    private final Logger log= Logger.getLogger(StartAddCacheListener.class);
    
    @Autowired
    private RedisCacheUtil<Object> redisCache;
    
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent  event) 
    {
        //spring 启动的时候缓存城市和国家等信息
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext"))
        {
            System.out.println("555555555555555");
            User u=new User();
            u.setId(1);
            u.setName("刘德华");
            try{
            	redisCache.setCacheObject("user", u);
            }catch(Exception e){
            	e.printStackTrace();
            }
        	System.out.println(redisCache.toString());  
        }
    }
    
}
