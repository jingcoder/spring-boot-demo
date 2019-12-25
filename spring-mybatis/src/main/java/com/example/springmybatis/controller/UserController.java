package com.example.springmybatis.controller;

import com.example.springmybatis.mapper.UserMapper;
import com.example.springmybatis.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/15 15:34
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    /**
     * XML映射
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public String get(Integer id) {

        // XML映射
        User user = userMapper.selectByPrimaryKey(id);


        return user.getUserName();
    }

    /**
     * 注解映射
     * @param id
     * @return
     */
    @RequestMapping("/info")
    public String info(Integer id) {

        // 注解映射
        User user = userMapper.selectById(id);

        return user.getUserName();
    }


    /**
     *
     * @param page
     * @param pageSize
     * @param param
     * @return
     */
    @RequestMapping("/list")
    public String list(int page, int pageSize, String param) {

        return "";
    }
}
