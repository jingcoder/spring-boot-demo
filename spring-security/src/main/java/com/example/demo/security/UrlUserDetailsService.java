package com.example.demo.security;

import com.example.demo.common.MockTestData;
import com.example.demo.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/29 23:30
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Component
public class UrlUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
           // 从数据库获取用户
           // 此处之所以不用校验密码，是因为Spring 自动校验，特别是 User继承自 UserDetail
           User user = (User) MockTestData.loadUser(userName);

          if(user != null){

          }else {
              throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
          }
           return user;
    }



}
