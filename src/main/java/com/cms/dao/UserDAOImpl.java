package com.cms.dao;

import org.hibernate.Session;
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
}