package com.example.springmybatismapper4.model;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/17 22:55
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class BaseEntity {

    @Id
    private Integer id;

    @Column
    int delFlag;

    private Object distinct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public Object getDistinct() {
        return distinct;
    }

    public void setDistinct(Object distinct) {
        this.distinct = distinct;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
