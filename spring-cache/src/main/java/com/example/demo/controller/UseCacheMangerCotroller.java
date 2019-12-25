package com.example.demo.controller;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/28 15:16
 * @Email xu.xiaojing@frontsurf.com
 * @Description  本例子展示如何获取获取，手动使用缓存
 */

@RestController
public class UseCacheMangerCotroller {

    @Resource
    CacheManager cacheManager;

    EhCacheCacheManager ehCacheCacheManager = null;

    @PostConstruct
    public void init(){
        //可以转换，但是还是最好别转换使用，不利于切换缓存管理工具
        ehCacheCacheManager = (EhCacheCacheManager) cacheManager;
        System.out.println(cacheManager.getCacheNames().toArray().toString());
    }

    @GetMapping("/aa")
    public void ehCacheManagerTest(){

        for(String name : ehCacheCacheManager.getCacheNames()){
            System.out.println("----------"+name);
        }

        //获取获取Region（区域）
        Cache userCache = cacheManager.getCache("user");
        Cache commonCache = cacheManager.getCache("common");

        //插入缓存
        userCache.put("1","TOM");
        //获取缓存
        System.out.println("----------------------------"+userCache.get("1").get());
    }

    /**
     * Ehcache2.x 不支持read-Thought
     */
    @GetMapping("/bb")
    public void readThroughTest(){

//        org.ehcache.CacheManager =
    }
}
