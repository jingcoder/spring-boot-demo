package com.example.demo.config;

import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.expiry.ExpiryPolicy;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/29 15:09
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Configuration
public class CacheConfig {

    @Resource
    CacheManager cacheManager;

    public void aa(){
        org.ehcache.CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        Cache<String,String> myCache = cacheManager.createCache("mycache",CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100,MemoryUnit.MB)).withDispatcherConcurrency(4).withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,TimeUnit.SECONDS))));

    }

}
