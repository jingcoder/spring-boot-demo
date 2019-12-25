package com.example.demo.common.entity;

import javax.persistence.Embeddable;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/17 18:33
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

/**
 *  @Embeddable 介绍：
 * @Embeddable注解一个类的作用是把该类嵌入到目标类中，并把该类的属性映射到数据库表中的相应的字段。
 */
@Embeddable
public class Address {

    private String postCode;
    private String address;
    private String phone;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
}
