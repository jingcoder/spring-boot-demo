package com.example.demo.common.Listener;

import com.example.demo.common.entity.DataEntity;
import com.example.demo.entity.User;
import com.example.demo.utils.UserUtils;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/17 19:19
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

/**
 * 下面是实体类型的生命周期事件，与 @EntityListener 结合使用
 */
@Component
public class DataEntityListener {

    /**
     *  EntityManager 持久操作之前（save），要进行的操作
     *  1. 提供时间生成。创建者等，
     *  2. 可以提供主键的生成方案
     * @param dataEntity
     */
    @PrePersist
    public void preInsert(DataEntity dataEntity){
        User user = UserUtils.currentUser();
        if(user != null){
            dataEntity.setUpdateBy(user);
            dataEntity.setCreateBy(user);
        }
        Date now  = new Date();
        dataEntity.setCreateDate(now);
        dataEntity.setUpdateDate(now);
    }

    /**
     * 更新前（update），要进行的操作
     * @param dataEntity
     */
    @PreUpdate
    public void preUpdate(DataEntity dataEntity){
        User user = UserUtils.currentUser();
        if(user != null){
            dataEntity.setUpdateBy(user);
        }
        dataEntity.setUpdateDate(new Date());
    }

    /**
     *  EntityManager 删除操作之前，回调此方法
     *  @param dataEntity
     */
     @PreRemove
    public void preRemove(DataEntity dataEntity){
     }

    /**
     * 还有下面四种：
     *
     * @PostLoad ： 在返回或访问查询结果之前或在遍历关联之前调用该方法。
     *
     * @PostPersist : 持久化完成后，执行操作
     *
     * @PostRemove ：在数据库删除操作之后调用该方法。
     *
     * @PostUpdate ：在数据库更新操作之后调用该方法。
     *
     */






}
