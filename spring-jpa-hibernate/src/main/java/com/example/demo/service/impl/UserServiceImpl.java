package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/21 8:39
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@Service
public class UserServiceImpl {

    @Autowired
    EntityManager entityManager;

    public  void bb(@NonNull String aa){
        System.out.println("bbbbbbbb");
    }

    @NonNull
    public void aa(){
//        Session session = entityManager.unwrap(Session.class);
//        Criteria criteria = session.createCriteria(User.class);
//        Expression.eq()
//        entityManager.createNativeQuery()
        bb(null);
    }

    public static void main(String[] args) {
        new UserServiceImpl().aa();

    }
}
