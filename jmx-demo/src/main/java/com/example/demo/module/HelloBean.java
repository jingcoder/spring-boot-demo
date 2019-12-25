package com.example.demo.module;

import org.springframework.stereotype.Component;

import javax.management.MXBean;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/20 11:24
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Component
//@MXBean
public class HelloBean implements HelloMBean{
    private String name;
    private int cacheSize;
    private String readOnly;
    private String writeOnly;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getCacheSize() {
        return cacheSize;
    }

    @Override
    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void setWriteOnly(String writeOnly) {
        this.writeOnly = writeOnly;
    }

    public String getReadOnly() {
        return readOnly;
    }


}
