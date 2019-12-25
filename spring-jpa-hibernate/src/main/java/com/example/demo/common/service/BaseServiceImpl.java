package com.example.demo.common.service;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.entity.DataEntity;
import com.example.demo.common.entity.Pager;
import com.example.demo.common.query.CompareTypeEnum;
import com.example.demo.common.query.QueryConfig;
import com.example.demo.common.query.SqlQueryParams;
import com.example.demo.common.repository.BaseRepository;
import com.example.demo.utils.Reflections;
import com.example.demo.utils.UserUtils;
import org.hibernate.Criteria;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/16 10:37
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public abstract class BaseServiceImpl<T extends BaseEntity<ID>,ID extends  Serializable> implements BaseService<T,ID> {

    protected abstract BaseRepository<T,ID> getDao();
    private Class<T> persistentClass;

    public BaseServiceImpl() {
        super();
        this.persistentClass = Reflections.getClassGenricType(getClass(), 0);
    }

    @Override
    public T save(T t) {
        return getDao().save(t);
    }

/**
    //手动生成时间
 @Override
    public T save(T t){
        if(t instanceof DataEntity){
            DataEntity<ID> baseEntity = (DataEntity<ID>)t;
            Date current = new Date();
            if(baseEntity.getId() == null){
                baseEntity.setCreateDate(current);
                baseEntity.setCreateBy(UserUtils.currentUser());
                return getDao().save(t);
            }else {
                T t1 = getDao().findOne(t.getId());
                baseEntity.setUpdateDate(current);
                baseEntity.setUpdateBy(UserUtils.currentUser());
                BeanUtil.copyProperties(t, t1);
                return getDao().save(t1);
            }
        }

        return getDao().save(t);
    }*/

    @Override
    public Iterable save(Iterable entities) {

        return getDao().saveAll(entities);
    }

    @Override
    public void del(ID id) {
          T t = findById(id);
          if(t == null){
              return ;
          }
          del(t);
    }

    @Override
    public void del(T t) {
         if(t instanceof DataEntity){
             DataEntity dataEntity = (DataEntity) t;
             dataEntity.setDelFlag(DataEntity.DEL_FLAG_NORMAL);
             dataEntity.setUpdateBy(UserUtils.currentUser());
             dataEntity.setUpdateDate(new Date());
             getDao().save(t);
         }else {
             getDao().delete(t);
         }
    }

    @Override
    public T findById(ID id) {
        return getDao().findByIdAndDelFlag(id,DataEntity.DEL_FLAG_NORMAL);
    }

    @Override
    public List findAll() {
        return getDao().findByDelFlag(DataEntity.DEL_FLAG_NORMAL);
    }

    @Override
    public List findAll(Pageable pageable) {
//        getDao().f
        return null;
    }

    @Override
    public T saveOrUpdate(T t) {
        if(t instanceof DataEntity){
            DataEntity<ID> dataEntity = (DataEntity) t;
            if(dataEntity.getId() != null){
                T old = getDao().findByIdAndDelFlag(dataEntity.getId(),DataEntity.DEL_FLAG_NORMAL);
                if(old != null){
                    BeanUtils.copyProperties(t,old);
                    t = old;
                }
            }
        }
        return getDao().save(t);
    }

    @Override
    public List<T> list(List<SqlQueryParams> params) {
         if(params != null){
             for(SqlQueryParams sql : QueryConfig.defaultParams){
                 params.add(sql);
             }
         }else{
             params = QueryConfig.defaultParams;
         }



        return null;
    }

    @Override
    public Pager<T> list(List<SqlQueryParams> params, Pageable pageable) {
        return null;
    }

   private Specification getSpecification(final List<SqlQueryParams> params){
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new LinkedList<>();
                for(SqlQueryParams param : params){
                    Predicate predicate = getPredicate(param,root,cb);
                }
                return null;
            }
        };

        return  spec;
    }

    private static <T> Predicate getPredicate(SqlQueryParams param, Root<T> root, CriteriaBuilder cb) {
        CompareTypeEnum compareType = param.getCompareType();
        String paramName = param.getParamName();
        Object value = param.getValue();
        Predicate predicate;

        switch (compareType){
            case gt:
//                     predicate = cb.
        }

        return null;
    }


}
