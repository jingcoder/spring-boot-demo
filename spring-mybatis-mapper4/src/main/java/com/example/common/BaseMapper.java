package com.example.common;

import com.example.springmybatismapper4.model.BaseEntity;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/17 22:58
 * @Email xu.xiaojing@frontsurf.com
 * @Description  注意BaseMapper不能被MapperScan扫描到
 */


public interface BaseMapper<T extends BaseEntity> extends Mapper<T> {
}
