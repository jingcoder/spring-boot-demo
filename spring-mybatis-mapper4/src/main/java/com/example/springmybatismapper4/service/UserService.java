package com.example.springmybatismapper4.service;

import com.example.springmybatismapper4.model.UserEntity;
import org.springframework.stereotype.Service;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/18 8:41
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public interface UserService extends BaseService<UserEntity> {

   int  deleteByUserEntity(UserEntity userEntity);

   int delete(Integer id);
}
