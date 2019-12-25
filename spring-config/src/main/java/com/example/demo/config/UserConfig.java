package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/9 10:44
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Configuration
public class UserConfig {

    /**
     * @Value 可以用于 配置注解 @Configuration
     * 还可以用于Bean的注入 @Controller、@Compent、（@Service没验证）
     */
    @Value("${user.userName}")
    public static String userName;  //不能用static 修饰，注入失败

    @Value("${user.password}")
    private  String password;

    @Value("${user.roles}")
    private  String roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
