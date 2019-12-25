package com.example.demo.controller;

import com.example.demo.common.entity.Return;
import com.example.demo.security.SecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/28 16:37
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
public class UserController {

    @RequestMapping("/login/page")
    public Return loginPage(){

        return new Return(400,"登陆页面：尚未登录，请先登录");
    }

    @RequestMapping("/login/success")
    public Return loginSuccess(String userName, String password, HttpSession session){

     /*   if(password.equals("aa")){
            session.setAttribute();
        }*/
        return Return.success("登陆成功");
    }

    @RequestMapping("/login/fail")
    public Return loginFail(String userName, String password, HttpSession session){

     /*   if(password.equals("aa")){
            session.setAttribute();
        }*/
        return Return.fail("登陆失败");
    }

}
