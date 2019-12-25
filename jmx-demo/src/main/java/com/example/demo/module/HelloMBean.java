package com.example.demo.module;

import javax.management.MXBean;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/20 11:36
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@MXBean
public interface HelloMBean {

     String getName();

    public void setName(String name);

    public int getCacheSize();

    public void setCacheSize(int cacheSize);

}
