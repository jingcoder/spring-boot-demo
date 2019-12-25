package com.example.redisdemo.entity;

import java.io.Serializable;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/17 23:28
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class User implements Serializable {

    private Integer id = 1;
    private String userName = "xiaoming";
    private String password = "123";

    public User(){

    }


    public User(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
