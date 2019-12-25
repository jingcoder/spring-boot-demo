package com.example.springmybatismapper4.service;

import com.example.common.BaseMapper;
import com.example.springmybatismapper4.mapper.UserMapper;
import com.example.springmybatismapper4.model.UserEntity;
import com.example.springmybatismapper4.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/17 23:30
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    protected BaseMapper<UserEntity> getMapper() {
        return userMapper;
    }

    @Override
    protected Class getEntityClass() {
        return UserEntity.class;
    }


    @Override
    public int deleteByUserEntity(UserEntity userEntity) {
        return userMapper.delete(userEntity);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteById(id);
    }


    @Override
    public int deleteById(UserEntity userEntity) {
        return 0;
    }
}
