package com.example.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/9 15:37
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@Component
@ConfigurationProperties(prefix = "user")  // 使用Spring的配置文件(application.proprerties)进行配置属性，
public class User {
    private String userName;

    private String password;

    private String roles;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
