package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/9 11:11
 * @Email xu.xiaojing@frontsurf.com
 * @Description Spring 是没法直接注入到 静态变量中去，特别是配置文件中的值
 */

@Configuration
public class UserStaticConfig {


    public static String userName;


    public static String password;


    public static String roles;


    public static String getUserName() {
        return userName;
    }

    @Value("${user.userName}")
    public  void setUserName(String userName) {
        UserStaticConfig.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    @Value("${user.password}")
    public void setPassword(String password) {
        UserStaticConfig.password = password;
    }

    public static  String getRoles() {
        return roles;
    }

    @Value("${user.roles}")
    public  void setRoles(String roles) {
        UserStaticConfig.roles = roles;
    }
}
