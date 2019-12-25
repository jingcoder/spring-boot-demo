package com.example.demo;

import com.example.demo.controller.UseCacheMangerCotroller;
import com.example.demo.controller.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserController userController;

    @Resource
    CacheManager cacheManager;

    @Test
    public void contextLoads() {
        Integer id = 1;
        Assert.assertEquals(userController.getUser(id),"NO_CACHE");
        userController.addUser(id);
        Assert.assertEquals(userController.getUser(id),"Jack");
        userController.updateUser(id,"Jack");
        Assert.assertEquals(userController.getUser(id),"Tom");
        userController.deleteUser(id);
        Assert.assertEquals(userController.getUser(id),"NO_CACHE");

        // 不同区的CacheName
        Assert.assertEquals(userController.getCommonCacheUser(id),"common");
    }

    @Test
    public void printCacheName(){
        for(Object object : cacheManager.getCacheNames()){
            System.out.println("--------------" + object);
        }
    }


    @Autowired
    UseCacheMangerCotroller useCacheMangerCotroller;

    @Test
    public void test(){
        useCacheMangerCotroller.ehCacheManagerTest();
    }

}
