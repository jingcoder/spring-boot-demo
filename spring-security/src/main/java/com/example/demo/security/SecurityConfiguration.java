package com.example.demo.security;

import com.example.demo.security.Filter.UrlFilterSecurityInterceptor;
import com.example.demo.security.model.User;
import com.example.demo.security.monitor.CustomAuthenticationSuccesstHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/29 23:47
 * @Email xu.xiaojing@frontsurf.com
 * @Description 这是 Spring Security 必须配置的类,是Spring Security基础的配置类
 */

@Configuration
@EnableWebSecurity //
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UrlUserDetailsService urlUserDetailsService;

    @Autowired
    UrlFilterSecurityInterceptor urlFilterSecurityInterceptor;

    @Autowired
    CustomAuthenticationSuccesstHandler successtHandler;


    /**
     * Http 安全
     * HttpSecurity用于提供一系列的Security默认的Filter，最终在WebSecurity对象中，组装到最终产生的springSecurityFilterChain 对象中去；
     * 用于配置资源URL的安全性,设置资源的过滤器，
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().antMatchers("/login/success").permitAll();

        // 所有的请求都需要身份认证
//        http.authorizeRequests().antMatchers("/admin/*", "/demo/*").authenticated();

        // 登陆页面 以及 登陆处理的路径(以及定义参数名称)
//        http.formLogin().loginPage("/login/page").usernameParameter("username").passwordParameter("password").permitAll().successHandler(successtHandler);

        // 默认开启csrf防御，但这样会造成几乎只有get请求可以访问，其他请求如post就不能进行。如果不关掉，则每个请求加个token；
        // 参考 https://blog.csdn.net/sinat_28454173/article/details/52251004
        // TODO 必须的
        http.csrf().disable();

        // 必须是admin角色才能访问，但粒度比较粗，大部分情况不建议这样做
//        http.authorizeRequests().antMatchers("/admin/**").hasRole("admin");
        // 只要拥有任意一个角色
//        http.authorizeRequests().antMatchers("/admin_or_dba").hasAnyRole("ADMIN", "DBA");
        // 必须同时拥有 这两个角色
//        http.authorizeRequests().antMatchers("/demo/dba").access("hasRole('ADMIN') and hasRole('DBA')");
//        http.
        //
//        http.authorizeRequests().anyRequest().access("@mySecurity.check(authentication,request)");
        // 剩下的请求不需要任何认证，直接访问
//        http.authorizeRequests().anyRequest().permitAll();


        // 添加自定义的 过滤器
//        http.addFilterBefore(urlFilterSecurityInterceptor,FilterSecurityInterceptor.class);
//        http.add


        // http
    }

    /**
     * WEB 安全
     * WebSecurity 配置的是全局性，配置全局的过滤器，如 忽略某些资源，拒绝某些请求，设置调试模式 等
     * 所以 设置的过滤器 是要比 HttpSecurity 优先级高
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        super.configure(web);
    }

    /**
     * AuthenticationManagerBuilder 是身份认证管理生成器，用于生成身份认证的创建机制
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 身份认证的三种方式：
        // 1. 定义了内存的身份认证
        //  auth.inMemoryAuthentication().withUser("user").password("123").roles("USER").and().withUser("admin").password("1234").roles("ADMIN");
        //  2. 注册一个 身份认证提供者
//        auth.authenticationProvider();
        // 3. 提供一个UserDetailsService
        //必须设置密码的加密方式，这是spring 5的特性
        auth.userDetailsService(urlUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    User user = (User) authentication.getPrincipal();
//                    logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
//                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }

                httpServletResponse.sendRedirect("/login/fail");
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, ServletException, IOException {
                User userDetails = (User) authentication.getPrincipal();
                logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

}
