package com.example.demo.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/16 10:31
 * @Email xu.xiaojing@frontsurf.com
 * @Description  最基础的类，负责：序列化、ID的生成策略
 */

/**  serialVersionUID 的介绍：
 *
 * 1. Java的序列化机制是通过判断类的serialVersionUID来验证版本一致性的。
 *
 * 2. 在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体类的serialVersionUID进行比较，
 * 如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常，即是InvalidCastException。
 */

/**
 *  @MappedSuperclass 介绍：
 *
 * 1. @MappedSuperclass注解使用在父类上面，是用来标识父类的
 *
 * 2. @MappedSuperclass标识的类表示其不是完整的实体类，不能映射到数据库表（即不能再有@Entity或@Table注解），但是它所拥有的属性能够隐射在其子类对用的数据库表中
 *
 * 3. 但是如果一个标注为@MappedSuperclass的类继承了另外一个实体类或者另外一个同样标注了@MappedSuperclass的类的话，
 * 他将可以使用@AttributeOverride或@AttributeOverrides注解重定义其父类(无论是否是实体类)的属性映射到数据库表中的字段。
 */

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    private ID id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

 /*   @Transient
    @JsonIgnore
    @XmlTransient
    public User getCurrentUser() {
        if(currentUser == null){
            currentUser = UserUtils.currentUser();
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }*/

}
