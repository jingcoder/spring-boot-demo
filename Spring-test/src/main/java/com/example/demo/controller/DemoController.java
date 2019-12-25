package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/13 9:44
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@RestController
public class DemoController {

    @RequestMapping("/test")
    public String test(){
        return "success";
    }
}
