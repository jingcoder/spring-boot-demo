package com.example.springmybatis.mapper;

import com.example.springmybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from user where id =  #{id}")
    @Results(id = "userMap",value = {
            @Result(column = "id",property = "id",javaType = Integer.class),
            @Result(column = "user_name",property = "userName",javaType = String.class)
    })
    User selectById(@Param("id") int id);
}