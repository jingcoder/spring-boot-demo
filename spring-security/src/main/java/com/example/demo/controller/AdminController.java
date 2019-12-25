package com.example.demo.controller;

import com.example.demo.common.entity.Return;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/7 20:58
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/dba")
    public Return adminAndDba(){
        return Return.success("admin 和 dba 交集权限");
    }

    @RequestMapping("/bbb")
    public Return bbb(){
        return Return.success("admin 下的 bbb~~~");
    }

}
