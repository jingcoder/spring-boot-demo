package com.example.demo.common.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/16 10:36
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

/**
 * 因为继承了  接口JpaSpecificationExecutor等接口，所以要加@NoRepositoryBean，让 Spring Data Jpa在启动时就不会去实例化BaseRepository这个接口
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaSpecificationExecutor<T>,JpaRepository<T,ID> {

    List<T> findByDelFlag(String delFlag);

    T findByIdAndDelFlag(ID id,String delFlag);

    @Override
    List<T> findAll(Sort sort);


}
