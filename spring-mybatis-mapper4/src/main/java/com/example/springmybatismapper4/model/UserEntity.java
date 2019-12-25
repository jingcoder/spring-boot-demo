package com.example.springmybatismapper4.model;

import javax.persistence.Table;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/17 22:56
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@Table(name = "user")
public class UserEntity extends BaseEntity{

    private String userName;

    private String address;

    private String phone;

    private String email;

    private Integer roleId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
