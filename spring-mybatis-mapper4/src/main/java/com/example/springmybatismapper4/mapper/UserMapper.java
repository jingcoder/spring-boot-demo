package com.example.springmybatismapper4.mapper;

import com.example.common.BaseMapper;
import com.example.springmybatismapper4.model.BaseEntity;
import com.example.springmybatismapper4.model.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/17 23:27
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public interface UserMapper extends BaseMapper<UserEntity> {

    @Update("update user set del_flag=1 where id = #{id}")
    int deleteById(Integer id);


}
