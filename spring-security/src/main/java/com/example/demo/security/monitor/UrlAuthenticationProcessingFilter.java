package com.example.demo.security.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/10 8:55
 * @Email xu.xiaojing@frontsurf.com
 * @Description 此类可以自定义登录校验，但一般不需要这样做
 */

// @Component
public class UrlAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    private void setCustomAuthenticationSuccesstHandler(CustomAuthenticationSuccesstHandler mySuccesstHandler){
        super.setAuthenticationSuccessHandler(mySuccesstHandler);
    }

    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    // 必须要指定 AuthenticationManager 的实现

 }
