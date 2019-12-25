package com.example.springmybatismapper4.controller;

import com.example.springmybatismapper4.mapper.UserMapper;
import com.example.springmybatismapper4.model.BaseEntity;
import com.example.springmybatismapper4.model.UserEntity;
import com.example.springmybatismapper4.service.UserService;
import com.example.springmybatismapper4.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Resource(name="userServiceImpl")
    UserService userServiceImpl;

    @Autowired
    UserMapper userMapper;





    @RequestMapping("/mapper")
    public String mapperTool(Integer id){

        UserEntity u1 = userMapper.selectByPrimaryKey(id);
        UserEntity userEntity = userServiceImpl.selectById(id);

        return userEntity.getUserName();
    }

    @RequestMapping("/delete")
    public String deleteUser(Integer id){

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        int i = userServiceImpl.deleteByUserEntity(userEntity);

        return "success";
    }

    @RequestMapping("/deleteById")
    public String deleteUserById(Integer id){

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        int i = userServiceImpl.delete(id);

        return "success";
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
