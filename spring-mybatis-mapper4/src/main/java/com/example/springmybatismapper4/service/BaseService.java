package com.example.springmybatismapper4.service;

import com.example.common.BaseMapper;
import com.example.springmybatismapper4.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/17 23:27
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public interface BaseService<T extends BaseEntity> {

    int save(T t);

    T selectById(Integer id);

    int deleteById(T t);


}
