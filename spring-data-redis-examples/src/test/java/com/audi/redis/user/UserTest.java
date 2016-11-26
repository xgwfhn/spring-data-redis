package com.audi.redis.user;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.CascadeType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.audi.redis.model.User;
import com.audi.redis.repository.UserDao;
import com.audi.redis.service.RedisCacheUtil;


   

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class UserTest extends AbstractJUnit4SpringContextTests {
	@Resource
    private UserDao userDao;  
	
	@Autowired
    private RedisCacheUtil<User> redisCache;
     
    @Test
    public void saveTest() { 
    	// ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
        // UserDao userDAO = (UserDao)ac.getBean("userDAO");
         
    	 User user1 = new User();
         user1.setId(1);
         user1.setName("obama");
         userDao.saveUser(user1);
         User user2 = userDao.getUser(1);
         System.out.println(user2.getName());
    }
    
    @Test
    public void testGetCache()
    {
        /*Map<String,Country> countryMap = redisCacheUtil1.getCacheMap("country");
        Map<String,City> cityMap = redisCacheUtil.getCacheMap("city");*/
    	User u=redisCache.getCacheObject("user");
    	System.out.println(u.getName());
        
        
    }  
}
