package com.example.demo.controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/23 15:26
 * @Email xu.xiaojing@frontsurf.com
 * @Description 本例子展示：如何四个方法级别的注解
 */

// 可以全局设置CacheName，也可以在 在每一个具体操作缓存的注解上，指定。
@CacheConfig(cacheNames = {"common"})
@RestController
public class UserController {

    /**
     * 设置缓存，默认 KEY便是参数值，VALUE 是 返回值.
     *
     * @param id
     * @return
     */
    @CachePut(cacheNames = "user")
    public String addUser(Integer id){

        return "Jack";
    }

    /**
     * KEY 默认是（一个或多个）参数的值的组合，如果想指定是第N个参数作为KEY，则才可以
     * @param id
     * @return
     */
    @CachePut(key = "#p0",cacheNames = "user")
    public String updateUser(Integer id,String userName){

        return "Tom";
    }

    /**
     * 如果缓存存在，则不执行方法，否则就执行方法，设置缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "user")
    public String getUser(Integer id){

        return "NO_CACHE";
    }

    /**
     * 缓存删除
     * @param id
     * @return
     */
    @CacheEvict(cacheNames = "user")
    public String deleteUser(Integer id){

        return "DELETE SUCCESS";
    }

    /**
     *  使用全局的cacheName common
     */
    @Cacheable(key = "#p0")
    public String getCommonCacheUser(Integer id){

        return "common";
    }
}
