package com.example.demo.controller;

import com.example.demo.config.CompanyConfig;
import com.example.demo.config.User;
import com.example.demo.config.UserConfig;
import com.example.demo.config.UserStaticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/9 10:49
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
public class DemoController {

    @Autowired
    UserConfig userConfig;

    @Autowired
    User user;

    @Autowired
    CompanyConfig companyConfig;

    private String url;



    @Value("${system.url}")
    private void setSystemUrl(String url){
        this.url=url;
    }

    @RequestMapping("/url")
    public String getSystemUrl(){
        return url;
    }



    @RequestMapping("/config")
    public String getConfig(){

        return userConfig.getUserName()+"："+userConfig.getRoles()+"："+userConfig.getPassword();
    }

    @RequestMapping("/static/config")
    public String getStaticConfig(){
        return UserStaticConfig.userName +" : "+UserStaticConfig.roles +" : "+UserStaticConfig.password;
    }

    @RequestMapping("/company")
    public String getCompany(){
        return companyConfig.getBoss()+"："+companyConfig.getUser().getUserName();
    }

    @RequestMapping("/user")
    public String getUser(){

       return user.getUserName() +"："+user.getPassword();
    }



}
