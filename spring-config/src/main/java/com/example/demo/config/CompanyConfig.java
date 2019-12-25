package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/9 15:41
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@Configuration ////这是一个配置类，与@Service、@Component的效果类似
//@ConditionalOnBean(User.class)
@EnableConfigurationProperties(User.class) //通过这个注解, 将User这个类的配置到上下文环境中,本类中使用的@Autowired 注入到本类中，也可以注入到其他Bean中
public class CompanyConfig {

    @Value("${company.ali.department}")
    private String department;
    @Value("${company.ali.address}")
    private String address;
    @Value("${company.ali.boss}")
    private String boss;

    @Autowired
    private User user;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
