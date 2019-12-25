package com.example.demo.controller;

import com.example.demo.module.HelloBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/20 11:22
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
public class DemoController {

    @Autowired
    HelloBean helloBean;

    public int size = 1000;
    public String name = "ss";

    @RequestMapping("/hello")
   public HelloBean getJMXHelloBean(){

        return helloBean;
    }

    @Bean
    public MBeanExporter mbeanExporter(DemoController demoController,HelloBean helloBean) {
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<String, Object>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,10,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        beans.put("spitter:name=demoController",demoController);
        beans.put("spitter:name=HelloBean",helloBean);
        beans.put("spitter:name=threadPool",threadPoolExecutor);
        exporter.setBeans(beans);
        return exporter;
    }

    public HelloBean getHelloBean() {
        return helloBean;
    }

    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }
}
