package com.cms.dao;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cms.entity.User;
import com.cms.util.HibernateUtil;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public User login(String username, String password) {

        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        String hql = "FROM User WHERE username=:u AND password=:p";

        User user = session.createQuery(hql, User.class)
                .setParameter("u", username)
                .setParameter("p", password)
                .uniqueResult();

        session.close();

        return user;
    }
    
    
//    @PostConstruct
//    public void insertManager() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        User manager = new User();
//        manager.setUsername("manager1");
//        manager.setPassword(encoder.encode("manager123"));
//        manager.setEmail("manager@email.com");
//        manager.setFullName("Claims Manager");
//        manager.setRole("MANAGER");
//        session.save(manager);
//        session.getTransaction().commit();
//        session.close();
//        System.out.println("Manager inserted");
//    }
}