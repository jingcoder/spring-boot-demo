package com.example.springmybatismapper4.service.impl;

import com.example.common.BaseMapper;
import com.example.springmybatismapper4.model.BaseEntity;
import com.example.springmybatismapper4.service.BaseService;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author xu.xiaojing
 * @Date 2019/1/18 8:19
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {


    protected abstract BaseMapper<T> getMapper();

    protected abstract Class getEntityClass();

    @Override
    public int save(T t) {
        t.setDelFlag(0);
        return getMapper().insert(t);
    }

    @Override
    public T selectById(Integer id) {
        Example example = new Example(getEntityClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);
        criteria.andEqualTo("delFlag",0);
        return getMapper().selectOneByExample(example);
    }

    //    @Override
    public int delete(T t) {
        t.setDelFlag(0);
        return getMapper().updateByPrimaryKey(t);
    }
}
