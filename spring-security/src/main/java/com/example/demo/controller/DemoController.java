package com.example.demo.controller;

import com.example.demo.common.entity.Return;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/28 16:37
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/something")
    public Return getSomething(){

        return Return.success("aaa");
    }

}
